import classes from "./SearchResult.module.css"
import React from "react"
import User from "../../images/person-1.jpeg"

function SearchResult(props) {

    return (
        <div className={classes.candidateWrapper}>
            <div className={classes.personalInfo}>
                <div className={classes.image}>
                    <img src={User}></img>
                </div>
                <div className={classes.info}>
                    <h3 className={classes.caption}> {props.candidate.firstName} {props.candidate.lastName}</h3>
                    <h3 className={classes.caption}> {props.candidate.degree} </h3>
                </div>
            </div>
            <div className={classes.contactInfo}>
                <div>
                    {
                        props.candidate.higlight !== '' ? 
                        (<div>{props.candidate.highlight}</div>) : (<div></div> )
                    }
                </div>
            </div>
        </div>
    );

}

export default SearchResult;