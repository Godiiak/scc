import React, {useState} from "react";
import CurrencyListItem from "./CurrencyListItem";

export default function CurrencyList(props){

    const [selectCurrency, setSelectCurrency] = useState(props.initValue);

    return(
        <form>
            <div className="form-group">
                <label htmlFor="formControlCurrencySelect">{props.title}</label>
                <select className="form-control" id={props.id}
                        onChange={e => setSelectCurrency(e.target.value && props.updateSelectedCurrency(e.target.value))}
                        value={selectCurrency}>
                    {props.currencies.map((currency, index) => <CurrencyListItem currency={currency} key={index}/>)}
                </select>
            </div>
        </form>
    )
}