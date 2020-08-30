import React, {useEffect, useState} from "react";
import HistoryEntry from "./HistoryEntry";
import HistoryService from "../services/HistoryService";

export default function History(){
    const [curreciesHistory, setCurrenciesHistory] = useState([
        {fromCurrency: "",
        toCurrency: "",
        amount: "",
        result: "",
        date: ""}
    ]);

    useEffect(() => {
        HistoryService.getHistory().then(response => setCurrenciesHistory(response.data));
    }, [])

    return(
        <div>
            <h5>Currency conversion history:</h5>
            <table className="table table-sm mt-4">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">From currency</th>
                        <th scope="col">To currency</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Result amount</th>
                        <th scope="col">Date</th>
                    </tr>
                </thead>
                <tbody>
                     {curreciesHistory.map((entry, index) => <HistoryEntry entry={entry} key={index} index={index} />)}
                </tbody>
            </table>
        </div>
    )
}