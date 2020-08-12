import axios from 'axios';

export async function analyseImage(imageFile) {
    const formData = new FormData();
    formData.append("imageFile", imageFile)

    const response = await axios.post("/api/image/analyse", formData, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    })
    return response.data;
}
