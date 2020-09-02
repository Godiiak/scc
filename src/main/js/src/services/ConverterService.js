import axios from 'axios';
import authHeader from "./AuthHeader";

const API_URL = 'http://localhost:8080/';

class ConverterService {
    conversionCurrencies(data){
        if (authHeader().Authorization !== undefined){
            return axios.post(API_URL + 'convert_currency', data,
                {
                    headers: {'Content-Type': 'application/json',
                              'Authorization': authHeader().Authorization
                    }
                })
        }else {
            return axios.post(API_URL + 'convert_currency', data,
                {
                    headers: {'Content-Type': 'application/json'
                    }
                })
        }
    }
}

export default new ConverterService();