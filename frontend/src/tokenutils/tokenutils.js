export function myHeaderConf(){
    return {
        headers: {
            'Authorization' : `Bearer ${localStorage.getItem("jwt")}`
        }
    }
}