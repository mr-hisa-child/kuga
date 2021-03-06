const signin = (data) => {
    return post('/signin', data)
}

const signup = (data) => {
    return post('/signup', data)
}

const createTeam = (data) => {
    return post('/team', data)
}

const getMemberList = () => {
    const teamId = localStorage.getItem('kuga-team')
    return get(`/team/${teamId}/member`)
}

const getActivityList = (year) => {
    const teamId = localStorage.getItem('kuga-team')
    return get(`/team/${teamId}/activity?year=${year}`)
}

const deleteActivity = (activityId) => {
    return del(`/activity/${activityId}`)
}

const getScoreListByYear = (year) => {
    const teamId = localStorage.getItem('kuga-team')
    return get(`/team/${teamId}/score?year=${year}`)
}

const getScoreList = (activityId) => {
    return get(`/activity/${activityId}/score`)
}

const increment = (scoreId, category) => {
    return put(`/score/${scoreId}/increment/${category}`)
}

const decrement = (scoreId, category) => {
    return put(`/score/${scoreId}/decrement/${category}`)
}

const deleteMember = (memberId) => {
    return del(`/member/${memberId}`)
}

const saveMember = (data) => {
    if (data.id) {
        return put(`/member/${data.id}`, data)
    } else {
        const teamId = localStorage.getItem('kuga-team')
        return post(`/team/${teamId}/member`, data)
    }
}

const saveActivity = (data) => {
    if (data.id) {
        return put(`/activity/${data.id}`, data)
    } else {
        const teamId = localStorage.getItem('kuga-team')
        return post(`/team/${teamId}/activity`, data)
    }
}

const attend = (activityId, data) => {
    return put(`/activity/${activityId}/attend`, data)
}

const getTeam = () => {
    const teamId = localStorage.getItem('kuga-team')
    return get(`/team/${teamId}`)
    // return {
    //     status: 200,
    //     data: {
    //         teamId: 1,
    //         teamName: "FC.AVANZARE"
    //     }
    // }
}

const getTeamList = () => {
    return get(`/team`)
}

const post = async (path, params, option) => {
    return await window.$nuxt.$axios.post(path, params, option).catch(err => {
        return err.response
    })
}

const put = async (path, params, option) => {
    return await window.$nuxt.$axios.put(path, params, option).catch(err => {
        return err.response
    })
}

const del = async (path, params, option) => {
    return await window.$nuxt.$axios.delete(path, params, option).catch(err => {
        return err.response
    })
}

const get = async (path, option) => {
    return await window.$nuxt.$axios.get(path, option).catch(err => {
        return err.response
    })
}

export default {
    signin,
    signup,
    createTeam,
    getMemberList,
    getActivityList,
    getScoreList,
    increment,
    decrement,
    deleteMember,
    saveMember,
    getScoreListByYear,
    attend,
    deleteActivity,
    getTeam,
    getTeamList,
    saveActivity
}