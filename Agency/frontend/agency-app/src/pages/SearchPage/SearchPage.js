import classes from './SearchPage.module.css';
import React  from 'react';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { ElasticsearchService } from '../../service/ElasticsearchService';
import GeospatialSearch from '../../components/GeospatialSearch/GeospatialSearch';
import CandidateSearch from '../../components/CandidateSearch/CandidateSearch';
import SearchResult from '../../components/SearchResult/SearchResult';

function SearchPage() {

    const navigate = useNavigate();
    const [booleanOperation, setBooleanOperation] = useState();
   

    function handleOperationChange(e) {
        console.log('previous ' + booleanOperation)
        const targetValue = e.target.value;
        console.log('selected ' + targetValue)
        setBooleanOperation(previouseState => (targetValue));
        console.log('now ' + booleanOperation)
    } 

    function handleFieldSearchSubmit() {
        console.log(booleanOperation);
    }

    return (
        <div className={classes.pageWrapper}>
            <div className={classes.left}>
                <h1 className={classes.caption}> Find your perfect candidate</h1>
                
                <h3 className={classes.note}>Field search</h3>
                <CandidateSearch />
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
                <CandidateSearch />
                <div className={classes.btnDiv}>
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