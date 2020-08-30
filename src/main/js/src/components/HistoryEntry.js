import React from "react";

export default function HistoryEntry(props){
    return(
        <tr>
            <td>{props.index + 1}</td>
            <td>{props.entry.fromCurrency}</td>
            <td>{props.entry.toCurrency}</td>
            <td>{props.entry.amount}</td>
            <td>{props.entry.result}</td>
            <td>{props.entry.date}</td>
        </tr>
    )
}