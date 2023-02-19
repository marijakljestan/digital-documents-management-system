import classes from './GeospatialSearch.module.css';
import React  from 'react';
import { useState } from 'react';
import { ElasticsearchService } from '../../service/ElasticsearchService';

function GeospatialSearch() {

    const [geospatialFormData, setGeospatialFormData] = useState({city: "", radius: 0});

    function submithHandler(event) {
        event.preventDefault();
        ElasticsearchService.geospatialSearch(geospatialFormData);
    }

    function handleChange(e) {
        const targetId = e.target.id;
        const targetValue = e.target.value;
        
        if(targetId === 'city') {
            setGeospatialFormData(previousState => ({
                ...previousState,
                city: targetValue
            }));
        }
        else if(targetId === 'radius') {
            setGeospatialFormData(previousState => ({
                ...previousState,
                radius: parseFloat(targetValue)
            }));
        }
    }

    return(
        <form onSubmit={(e) => submithHandler(e)} className={classes.geospatialSearchDiv}>
            <h3 className={classes.note}> Geospatial search </h3>
            <div className={classes.geospatialFields}>
                <label className={classes.geoLabel}>City:</label>
                <div className={classes.search}>
                    <input type='text' className={classes.geospatial_search_input} onChange={handleChange} id='city'/>
                </div>
            </div>
            <div className={classes.geospatialFields}>
                <label className={classes.geoLabel}>Radius:</label>
                <div className={classes.search}>
                    <input type='text' className={classes.geospatial_search_input} onChange={handleChange} id='radius'/>
                </div>
            </div>
            <div className={classes.btnDiv}>
                <button type="submit" className={classes.button}> Search</button>
            </div>
        </form>
    );
}

export default GeospatialSearch;