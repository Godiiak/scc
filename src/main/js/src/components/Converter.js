import React, {useEffect, useState} from 'react';
import CurrencyList from "./CurrencyList";

export default function Converter() {
    const initValFormCurrency = "R01235";
    const initValToCurrency = "R01215";
    const endPointUrl = "http://localhost:8080";

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [currencies, setCurrencies] = useState([]);

    const [inputValue, setInputValue] = useState('100');
    const [fromCurrency, setFromCurrency] = useState(initValFormCurrency);
    const [toCurrency, setToCurrency] = useState(initValToCurrency);

    const [result, setResult] = useState('');

    const [statusButton, setStatusButton] = useState('')

    const handleValidation = (e) => {
        if(!e.match(/^[0-9]*[.,]?[0-9]+$/)){
            setStatusButton("disabled")
        }else {
            setStatusButton("")
        }
    }

    const calculateRate = () => {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ fromCurrencyId: fromCurrency, toCurrencyId: toCurrency, amount: inputValue })
        };
        return fetch(endPointUrl + '/convert_currency', requestOptions)
            .then(response => response.json())
            .then(data => setResult(data));
    }

    const updateSelectedFromCurrency = (e) => {
        setFromCurrency(e);
    }

    const updateSelectedToCurrency = (e) => {
        setToCurrency(e);
    }

    const handleInputValueChange = (e) => {
        handleValidation(e.target.value)
        setInputValue(e.target.value)
    }

    useEffect(() => {
        fetch(endPointUrl + "/get_currencies_list")
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setCurrencies(result);
                },
                (error) => {
                    setIsLoaded(true);
                    setError(error);
                }
            )
    }, [])

    if (error) {
        return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
        return <div>Loading...</div>;
    } else {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-sm">
                        <CurrencyList currencies={currencies} title="Convert from:" id={"fromCurrencySelect"} initValue={initValFormCurrency} updateSelectedCurrency={updateSelectedFromCurrency}/>
                    </div>
                    <div className="col-sm">
                        <CurrencyList currencies={currencies} title="Convert to:" id={"toCurrencySelect"} initValue={initValToCurrency} updateSelectedCurrency={updateSelectedToCurrency}/>
                    </div>
                </div>
                <div className="row">
                    <div className="col-sm">
                        <input type="text" className="form-control" id="inputValueToConvert"  value={inputValue} onChange={handleInputValueChange}/>
                    </div>
                    <div className="col-sm">
                        <input className="form-control" type="text" placeholder={result} readOnly/>
                    </div>
                </div>
                <div className="row">
                    <div className="col-sm mt-3">
                        <button type="submit" className="btn btn-primary" onClick={calculateRate} disabled={statusButton}>Convert</button>
                    </div>
                </div>
            </div>
        );
    }
}