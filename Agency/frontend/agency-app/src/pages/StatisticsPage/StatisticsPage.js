import classes from "./StatisticsPage.module.css"

function StatisticsPage() {

    return (
        <div className={classes.frame}>
            <iframe src="http://localhost:5601/app/kibana#/dashboard/44055d60-b16c-11ed-bab9-45df2e69d380?embed=true&_g=(filters%3A!()%2CrefreshInterval%3A(pause%3A!t%2Cvalue%3A0)%2Ctime%3A(from%3Anow-30d%2Cto%3Anow))" height="700" width="1600"></iframe>
        </div>
    )

}

export default StatisticsPage;