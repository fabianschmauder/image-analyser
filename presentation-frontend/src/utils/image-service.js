export const loadImageDataUrl = (file, urlDataCallback) => {
    const reader = new FileReader();
    reader.onload = (e) => {
        const img = new Image();

        img.onload = function () {
            // @ts-ignore
            urlDataCallback({data: e.target.result, width: img.width, height: img.height});
        };
        // @ts-ignore
        img.src = e.target.result;
    };
    reader.readAsDataURL(file);
};
