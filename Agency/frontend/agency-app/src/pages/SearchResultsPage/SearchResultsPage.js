import classes from './SearchResultsPage.module.css';
import React  from 'react';
import { useLocation } from 'react-router';
import SearchResult from '../../components/SearchResult/SearchResult'
import image from '../../images/search-engine.jpg'

function SearchResultsPage(props) {

    const location = useLocation();
    const candidates = location.state?.candidates;

    return (
        <div className={classes.pageWrapper}>
            <div className={classes.container}>
                <div className={classes.header}>
                    <h1 className={classes.caption}> Search results</h1>
                </div>
                {
                    candidates.map((candidate) =>
                        <SearchResult candidate={candidate}/>
                    )
                }
            </div>
            <div className={classes.rightContainer}>
                <img className={classes.imageWrapper} src={image} />
            </div>
        </div>
    );
}

export default SearchResultsPage;