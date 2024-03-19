import instance from "./axios";

export async function fetchRandomFoodList() {
    try {
        const res = await instance.get('/recommend')
        // const res = await axios.get(`${VITE_API_DEV}/recommend`)
        console.log('test')
        console.log(res)
    } catch (e) {
        console.log('test')
        console.log(e)
    }
    
}