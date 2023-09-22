const getters = {
    sidebar: state => state.app.sidebar,
    size: state => state.app.size,
    device: state => state.app.device,
    username: state => state.admin.username,
    sidebarRouters: state =>state.permission.sidebarRouters,
    isLoadingDynamicRoute: state =>state.permission.isLoadingDynamicRoute
}
export default getters
