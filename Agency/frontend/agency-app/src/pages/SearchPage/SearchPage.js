import classes from './SearchPage.module.css';
import React  from 'react';
import { useNavigate } from 'react-router-dom';
import CandidateSearch from '../../components/CandidateSearch/CandidateSearch';

function SearchPage() {

    const navigate = useNavigate();

    return (
        <div className={classes.pageWrapper}>
            <div className={classes.left}>
                <h1 className={classes.caption}> Find your perfect candidate</h1>
                <CandidateSearch />
            </div>
            <div className={classes.right}></div>
        </div>
    );

}

export default SearchPage;