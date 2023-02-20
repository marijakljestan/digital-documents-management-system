import classes from "./HomePage.module.css";
import React from "react";
import { useNavigate } from 'react-router-dom';

function HomePage() {

    const navigate = useNavigate();

    function handleStatistics() {
        navigate("/statistics");
    }

    function handleSearchCandidate() {
        navigate("/search");
    }

    function handleRegister() {
        navigate("/register");
    }

    return(
        <div className={classes.pageWrapper}>
            <div className={classes.navbar}>
                <div className={classes.leftSide}>
                    <h3 className={classes.navbarItem} onClick={handleSearchCandidate}> Candidate base</h3>
                    <h3 className={classes.navbarItem} onClick={handleStatistics}> Statistics</h3>
                    <h3 className={classes.navbarItem}> Contact</h3>
                </div>
                <div className={classes.rightSide}>
                <button className={classes.button} onClick={handleRegister}> Register here</button>
                </div>
            </div>
            <div className={classes.content}>
                <div className={classes.textContent}>
                    <h1 className={classes.caption}> Recruitment</h1>
                    <h1 className={classes.lighterCaption}> Agency</h1>
                    <div className={classes.description}>
                        The biggest base of jobs and candidates.
                    </div>
                </div>
            </div>
            <div className={classes.page}>
            </div>
        </div>
    );

}

export default HomePage;