import api from '@/utils/api'
export const state = () => ({
    scoreList: null
})

export const mutations = {
    save(state, payload) {
        state.scoreList = payload
    },
    update(state, payload) {
        state.scoreList.filter(score => score.id === payload.id).forEach(score => score = payload)
    }
}

export const actions = {
    findByActivityId({ commit }, activityId) {
        const res = api.getScoreList(activityId)
        if (res.status === 200) {
            commit('save', res.data)
        } else {

        }
    },
    findByYear({ commit }, year) {
        const res = api.getScoreListByYear(year)
        if (res.status === 200) {
            commit('save', res.data)
        } else {

        }
    },
    increment({ commit }, payload) {
        const res = api.increment(payload.scoreId, payload.category)
        if (res.status === 200) {
            commit('update', res.data)
        } else {

        }
    },
    decrement({ commit }, payload) {
        const res = api.decrement(payload.scoreId, payload.category)
        if (res.status === 200) {
            commit('update', res.data)
        } else {

        }
    }
}

export const getters = {
    scoreList: (state) => {
        return state.scoreList
    },
}