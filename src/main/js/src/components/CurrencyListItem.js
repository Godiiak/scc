import React from "react";

export default function CurrencyListItem(props){
    return(
        <option value={props.currency.id}>{props.currency.name} ({props.currency.charCode})</option>
    )
}