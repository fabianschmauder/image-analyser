import React from "react";
import {ImageDropzone} from "./ImageDropzone";



function ImageUploadContainer({onImageDrop}) {

    return <div style={{display:'flex', maxWidth: "400px"}}>

        <ImageDropzone onDropFile={(file) => {
            onImageDrop && onImageDrop(file);
        }}/>
    </div>
}

export default ImageUploadContainer;

