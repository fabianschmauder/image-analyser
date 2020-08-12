import React, {useState} from "react";
import ImageUploadContainer from "../components/ImageUploadContainer";
import {analyseImage} from "../service/image-service";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import Card from "@material-ui/core/Card";


function AnalyseResult({show, label}) {
    return <>
        {
            show && <Typography color="textSecondary" gutterBottom>
                {label}
            </Typography>
        }
    </>
}

function FaceAnalyseResult({face}) {
    return <>
        <AnalyseResult show={face.happy} label={"Du siehst Glücklich aus"}/>
        <AnalyseResult show={face.hasBeard} label={"Du solltest dich mal rasieren"}/>
    </>
}

function LandingPage() {
    const [result, setResult] = useState();

    return <div style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: "100%"}}>
        <Card>
            <CardContent>
                <Typography color="textSecondary" gutterBottom>
                    Lade dein Bild hoch
                </Typography>

                <ImageUploadContainer onImageDrop={(file) => {
                    analyseImage(file).then(setResult)
                }}/>

                {result && <FaceAnalyseResult face={result}/>}
            </CardContent>
        </Card>
    </div>
}

export default LandingPage;
