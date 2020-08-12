import axios from 'axios';

export async function analyseImage(imageFile) {
    const data = new FormData();
    data.append("imageFile", imageFile)
    const response = await axios.post("api/image/analyse", data, {
        headers: {
            "content-type": "application/form-data"
        }
    })

    return response.data;
}
