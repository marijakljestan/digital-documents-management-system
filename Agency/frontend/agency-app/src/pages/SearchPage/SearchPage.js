import classes from './SearchPage.module.css';
import React  from 'react';
import { useNavigate } from 'react-router-dom';
import CandidateSearch from '../../components/CandidateSearch/CandidateSearch';
import SearchResult from '../../components/SearchResult/SearchResult';

function SearchPage() {

    const navigate = useNavigate();

    return (
        <div className={classes.pageWrapper}>
            <div className={classes.left}>
                <h1 className={classes.caption}> Find your perfect candidate</h1>
                
                <h3 className={classes.note}>Field search</h3>
                <CandidateSearch />
                <label className={classes.note}> *Only if you want Boolean search, for regular search you can ignore this.</label>
                <div className={classes.operationDiv}>
                    <select className={classes.customSelect}>
                        <option selected disabled>Select operation</option>
                        <option value="AND">AND</option>
                        <option value="OR">OR</option>
                        <option value="NOT">NOT</option>
                    </select>
                </div>
                <label className={classes.note}> *If you're searching just by one field, you can ignore this.</label>
                <CandidateSearch />
                <div className={classes.btnDiv}>
                    <button className={classes.button}> Search</button>
                </div>
                
                <h3 className={classes.note}>Geospatial search</h3>
                <div className={classes.geospatialSearchDiv}>
                    <div className={classes.geospatialFields}>
                        <label className={classes.geoLabel}>City:</label>
                        <div className={classes.search}>
                            <input type='text' className={classes.geospatial_search_input}/>
                        </div>
                    </div>
                    <div className={classes.geospatialFields}>
                        <label className={classes.geoLabel}>Radius:</label>
                        <div className={classes.search}>
                            <input type='text' className={classes.geospatial_search_input}/>
                        </div>
                    </div>
                </div>
                <div className={classes.btnDiv}>
                    <button className={classes.button}> Search</button>
                </div>

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