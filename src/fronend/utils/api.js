const signin = (data) => {
    return post('/signin', data)
}

const createTeam = (data) => {
    return post('/team', data)
}

const getMemberList = () => {
    // const teamId = localStorage.getItem('team')
    // return get(`/team/${teamId}/member`)
    return {
        status: 200,
        data: [
            {
                id: '1234',
                name: '小野'
            },
            {
                id: '1234',
                name: '小野'
            },
            {
                id: '1234',
                name: '小野'
            },
            {
                id: '1234',
                name: '小野'
            },
            {
                id: '1234',
                name: '小野'
            },
            {
                id: '1234',
                name: '小野'
            },
            {
                id: '1234',
                name: '小野'
            },
            {
                id: '1234',
                name: '小野'
            },
            {
                id: '1234',
                name: '小野'
            },
            {
                id: '1234',
                name: '小野'
            },
            {
                id: '1234',
                name: '小野'
            },
            {
                id: '1234',
                name: '小野'
            },
            {
                id: '1234',
                name: '小野'
            },

        ]
    }
}

const getActivityList = (year) => {
    // const teamId = localStorage.getItem('team')
    // return get(`/team/${teamId}/activity?year=${year}`)
    return {
        status: 200,
        data: [
            {
                id: '1234',
                date: '2020-01-20',
                title: '練習',
                count: 5
            }
        ]
    }
}

const deleteActivity = (activityId) => {
    return del(`/activity/${activityId}`)
}

const getScoreListByYear = (year) => {
    // const teamId = localStorage.getItem('team')
    // return get(`/team/${teamId}/score?year=${year}`)
    return {
        status: 200,
        data: [
            {
                id: '1234',
                name: '小野',
                attend: 2,
                goal: 5,
                assist: 4,
                win: 2
            },
        ]
    }
}

const getScoreList = (activityId) => {
    // const teamId = localStorage.getItem('team')
    // return get(`/activity/${activityId}/score`)
    return {
        status: 200,
        data: [
            {
                id: '1234',
                name: '小野',
                goal: 5,
                assist: 4,
                win: 2
            },
            {
                id: '12345',
                name: '小野',
                goal: 5,
                assist: 4,
                win: 2
            },
            {
                id: '12346',
                name: '小野',
                goal: 5,
                assist: 4,
                win: 2
            },
            {
                id: '12346',
                name: '小野',
                goal: 5,
                assist: 4,
                win: 2
            },
            {
                id: '12346',
                name: '小野',
                goal: 5,
                assist: 4,
                win: 2
            },
            {
                id: '12346',
                name: '小野',
                goal: 5,
                assist: 4,
                win: 2
            },
            {
                id: '12346',
                name: '小野',
                goal: 5,
                assist: 4,
                win: 2
            },
            {
                id: '12346',
                name: '小野',
                goal: 5,
                assist: 4,
                win: 2
            },
            {
                id: '12346',
                name: '小野',
                goal: 5,
                assist: 4,
                win: 2
            },
            {
                id: '12346',
                name: '小野',
                goal: 5,
                assist: 4,
                win: 2
            },
            {
                id: '12346',
                name: '小野',
                goal: 5,
                assist: 4,
                win: 2
            },
        ]
    }
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
        return post(`/member`, data)
    }
}

const attend = (activityId, data) => {
    return put(`/activity/${activityId}/attend`, data)
}

const getTeam = (teamId) => {
    // return get(`/team/${teamId}`)
    return {
        status: 200,
        data: {
            teamId: 1,
            teamName: "FC.AVANZARE"
        }
    }
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
    getTeam
}