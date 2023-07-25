import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router';
// import { staticRoutes } from './staticRoutes';
// import defaultRoutes from './defaultRoutes';

// const routes: any = staticRoutes.concat(defaultRoutes);

const routes = [
    {
        path: "/index",
        name: "首页",
        component: Inner,
        meta: {
            activePath: '/'  // 打开非Menu页面选择当前激活menu
        }
    },
    {
        path: '/:pathMatch(.*)*',
        name: '404',
        component: NotFound
    },
    {
        path: '/login',
        name: '登录',
        component: Login
    }
];


const router = createRouter({
    history: createWebHashHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    let userInfo = localStorage.getItem('user');
    if (to.path === "/login") {
        next();
    } else {
        if (userInfo) {
            next();
        } else {
            next({
                path: '/login'
            });
        }
    }
});
export default router;