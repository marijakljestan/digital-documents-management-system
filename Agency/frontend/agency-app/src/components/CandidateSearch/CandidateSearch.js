import React from 'react';
import classes from './CandidateSearch.module.css';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

function CandidateSearch(props) {
    return (
        <div className={classes.component}>
            <div className = {classes.selectField}>
                    <select className={classes.customSelect}>
                        <option selected disabled>Select field</option>
                        <option value="firstName">First name</option>
                        <option value="lastName">Last name</option>
                        <option value="degree">Degree</option>
                        <option value="cvContent">CV content</option>
                        <option value="coverLetterContent">Cover letter content</option>
                    </select>
            </div>
            <div className={classes.search}>
                <FontAwesomeIcon icon={faMagnifyingGlass} className={classes.icon} />
                <input type='text' placeholder={props.placeholder} />
            </div>
        </div>
    )
}

export default CandidateSearch;