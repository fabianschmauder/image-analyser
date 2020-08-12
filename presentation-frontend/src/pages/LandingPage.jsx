import React, {useState} from "react";
import ImageUploadContainer from "../components/ImageUploadContainer";
import {analyseImage} from "../service/image-analyse-service";
import Typography from "@material-ui/core/Typography";

function determineWelcomeText(analyseData) {
    if (analyseData.happy) {
        return "Hey du siehst Glücklich aus";
    }
    return "Hey du siehst nicht so Glücklich aus";
}

function WelcomeMessage({analyseData}) {
    return <Typography color="textSecondary" gutterBottom>
        {determineWelcomeText(analyseData)}
    </Typography>
}

function LandingPage() {
    const [analyseData, setAnalyseData] = useState();
    return <main>
        <ImageUploadContainer onImageDrop={(image) => {
            analyseImage(image).then(setAnalyseData)
        }}/>
        {analyseData && <WelcomeMessage analyseData={analyseData}/>}
    </main>
}

export default LandingPage;
