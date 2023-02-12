import classes from "./SearchResult.module.css"
import React from "react"
import User from "../../images/person-1.jpeg"

function SearchResult() {

    return (
        <div className={classes.candidateWrapper}>
            <div className={classes.personalInfo}>
                <div className={classes.image}>
                    <img src={User}></img>
                </div>
                <div className={classes.info}>
                    <h3 className={classes.caption}> Marija Kljestan</h3>
                    <h3 className={classes.caption}> DevOps Engineer</h3>
                </div>
            </div>
            <div className={classes.contactInfo}>
                <label >Contact info: </label>
                <label> marijakljestan@gmail.com</label>
                <label> 065123456789</label>
                <label> Bulevar Despota Stefana 5a Novi Sad</label>
            </div>
        </div>
    );

}

export default SearchResult;