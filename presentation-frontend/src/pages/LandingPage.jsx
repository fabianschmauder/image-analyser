import React, {useState} from "react";
import ImageUploadContainer from "../components/ImageUploadContainer";
import {analyseImage} from "../service/image-analyse-service";
import Typography from "@material-ui/core/Typography";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import "./LandingPage.css"
function determineWelcomeText(analyseData) {
    if(analyseData.hasBeard) {
        return "Rasier dich mal 🪒"
    }
    if (analyseData.happy) {
        return "Hey du siehst Glücklich aus 😊";
    }
    return "Hey du siehst nicht so Glücklich aus 😢";
}

function WelcomeMessage({analyseData}) {
    return <Typography color="textSecondary" gutterBottom>
        {determineWelcomeText(analyseData)}
    </Typography>
}

function LandingPage() {
    const [analyseData, setAnalyseData] = useState();
    return <main className="App">
            <Card>
                <CardContent>
                    <Typography color="textSecondary" gutterBottom>
                        Lade dein Bild hoch
                    </Typography>

                    <ImageUploadContainer onImageDrop={(image) => {
                        analyseImage(image).then(setAnalyseData)
                    }}/>
                    {analyseData && <WelcomeMessage analyseData={analyseData}/>}
                </CardContent>
            </Card>

    </main>
}

export default LandingPage;
