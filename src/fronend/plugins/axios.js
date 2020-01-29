export default function ({ $axios, redirect }) {
    $axios.onRequest(config => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers.common['Authorization'] = token;
        }
    })
    $axios.onError(error => {
        console.log('axios request error:' + error)
    })

    $axios.onResponse(config => {
        // if (config.headers.authorization) {
        //   localStorage.setItem('kintai-token', config.headers.authorization);
        // }
    })
}