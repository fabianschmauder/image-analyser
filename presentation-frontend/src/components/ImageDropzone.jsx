import React, {useCallback, useState} from 'react'
import {useDropzone} from 'react-dropzone'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faImage, faImages} from '@fortawesome/free-solid-svg-icons';
import {loadImageDataUrl} from "../utils/image-service";

export const ImageDropzone = ({onDropFile}) => {
    const [imageData, setImageData] = useState({data: undefined, width: 0, height: 0});
    const onDrop = useCallback(acceptedFiles => {
        if (acceptedFiles.length > 0) {
            onDropFile(acceptedFiles[0]);
            loadImageDataUrl(acceptedFiles[0], (dataUrl) => setImageData(dataUrl));
        }
    }, [onDropFile]);
    const {getRootProps, getInputProps, isDragActive} = useDropzone({
        onDrop,
        multiple: false,
        accept: "image/*"
    });

    return (
        <div {...getRootProps()} style={{minWidth:'100px',minHeight:'100px', display:'flex', alignItems: 'center', justifyContent: 'center'}}>
            <input {...getInputProps()} />
            {
                imageData.data ? <div/> : (isDragActive ? <div style={{textAlign: 'center'}}>
                    <FontAwesomeIcon icon={faImage} style={{
                        color: '#000000',
                        fontSize: '1.25rem'
                    }}/>
                </div> : <div style={{textAlign: 'center'}}>
                    <FontAwesomeIcon icon={faImages} style={{
                        color: '#000000',
                        fontSize: '1.25rem'
                    }}/>
                </div>)
            }
            {imageData.data && <img src={imageData.data} width='100%' alt='upload'/>}
        </div>
    )
};
