import React from "react";
import ImageUploadContainer from "../components/ImageUploadContainer";
import {analyseImage} from "../service/image-analyse-service";

function LandingPage() {
    return <main>
        <ImageUploadContainer onImageDrop={(image) => {
            analyseImage(image)
        }}/>
    </main>
}

export default LandingPage;
