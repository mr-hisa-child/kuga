import api from "@/utils/api";

export default async function ({ store, redirect, route, error }) {
    if (route.path.indexOf('/signin') >= 0 || route.path.indexOf('/signup') >= 0 || route.path.indexOf('/team') >= 0) {
        return Promise.resolve()
    }

    const activeTeam = store.getters['team/activeTeam']
    if (!activeTeam) {
        const res = await api.getTeamList()
        if (res.status === 200) {
            if (res.data.length === 0) {
                return redirect('/team')
            } else {
                store.commit('team/save', res.data)
            }
            return redirect('/member')
        } else {
        }
    }

    return Promise.resolve()
}