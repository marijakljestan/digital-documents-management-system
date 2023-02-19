import classes from './SearchResultsPage.module.css';
import React  from 'react';
import { useLocation } from 'react-router';
import SearchResult from '../../components/SearchResult/SearchResult'

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
        </div>
    );
}

export default SearchResultsPage;