export default {
    namespaced: true,
    state: {
        DocsInfo: []
    },
    getters: {},
    mutations: {
        SET_DOCS_INFO(state, value) {
            state.DocsInfo = value
        }
    },
    actions: {
        demo(context) {
            context.commit('SET_DOCS_INFO')
        }
    },
}
