import classes from './SearchPage.module.css';
import React  from 'react';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { ElasticsearchService } from '../../service/ElasticsearchService';
import GeospatialSearch from '../../components/GeospatialSearch/GeospatialSearch';
import CandidateSearch from '../../components/CandidateSearch/CandidateSearch';

function SearchPage() {

    const navigate = useNavigate();
    const [firstQuery, setFirstQuery] = useState({field: "", value: ""});
    const [secondQuery, setSecondQuery] = useState({field: "", value: ""});
    const [booleanOperation, setBooleanOperation] = useState();
    const [enabledPhraseSearch, setEnabledPhraseSearch] = useState(false);
    const [searchResults, setSearchResults] = useState([]);

    async function handleFieldSearchSubmit() {
        
        var data;
        if(booleanOperation) {
            data = await ElasticsearchService.booleanSearch(firstQuery, secondQuery, booleanOperation);
        } 
        else if(enabledPhraseSearch) {
            data = await ElasticsearchService.searchByPhrase(firstQuery);
        } 
        else if(firstQuery.field === "cvContent") {
            data = await ElasticsearchService.searchByCVContent(firstQuery.value);
        } 
        else if (firstQuery.field === "coverLetterContent") {
            data = await ElasticsearchService.searchByCoverLetterContent(firstQuery.value);
        } 
        else if(secondQuery.field !== "") {
            data = await ElasticsearchService.searchByFields(firstQuery, secondQuery);
        } 
        else {
            data = await ElasticsearchService.searchByField(firstQuery);
            setSearchResults(data);
        }
        navigate('/search-results', { state: { candidates: data }})
    }

    function handleFirstFieldChange(e) {
        setFirstQuery(previousState => ({
            ...previousState,
            field: e.target.value
        }));
    }

    function handleFirstValueChange(e) {
        setFirstQuery(previousState => ({
            ...previousState,
            value: e.target.value
        }));
    }

    function handleSecondFieldChange(e) {
        setSecondQuery(previousState => ({
            ...previousState,
            field: e.target.value
        }));
    }

    function handleSecondValueChange(e) {
        setSecondQuery(previousState => ({
            ...previousState,
            value: e.target.value
        }));
    }

    function handleOperationChange(e) {
        setBooleanOperation(previouseState => (e.target.value));
    } 


    return (
        <div className={classes.pageWrapper}>
            <div className={classes.left}>
                <div className={classes.header}>
                    <h1 className={classes.caption}> Find your perfect candidate</h1>
                </div>
                <h3 className={classes.note}>Field search</h3>
                <CandidateSearch handleFieldChange={handleFirstFieldChange} handleValueChange={handleFirstValueChange}/>
                <label className={classes.note}> *Only if you want Boolean search, for regular search you can ignore this.</label>
                <div className={classes.operationDiv}>
                    <select className={classes.customSelect} defaultValue="" onChange={handleOperationChange}>
                        <option value="" disabled>Select operation</option>
                        <option value="AND">AND</option>
                        <option value="OR">OR</option>
                        <option value="NOT">NOT</option>
                    </select>
                </div>
                <label className={classes.note}> *If you're searching just by one field, you can ignore this.</label>
                <CandidateSearch handleFieldChange={handleSecondFieldChange} handleValueChange={handleSecondValueChange}/>
                <div className={classes.btnDiv}>
                    <div className={classes.checkboxDiv}>
                        <input type="checkbox" onChange={() => setEnabledPhraseSearch(!enabledPhraseSearch)}/> 
                        <label className={classes.note}> Enable phrase search</label>
                    </div>
                    <button className={classes.button} onClick={handleFieldSearchSubmit}> Search</button>
                </div>
                
                <GeospatialSearch/>

                <div className={classes.searchResults}>
                </div>
            </div>

            <div className={classes.rightSide}>
                <div className={classes.right}></div>
                <div className={classes.blank}></div>
            </div>
        </div>
    );
}

export default SearchPage;