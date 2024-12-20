import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
    scrollBehavior() {
        return {
            x: 0,
            y: 0
        }
    },
    base: process.env.BASE_URL,
    routes: [
        // ======================
        // Blank Layout
        // ======================
        {
            path: '',
            component: () => import('./layout/blank/Blank.vue'),
            // ======================
            // Theme routes / pages
            // ======================
            children: [{
                path: '/',
                redirect: '/view/diagnosis/Atm'
            }],
        },
        {
            // ======================
            // Full Layout
            // ======================
            path: '/main',
            component: () => import('./layout/full/MainContainer.vue'),
            children: [ {
                path: '/view/diagnosis/atm',
                name: 'atmconnection',
                index: 1,
                component: () => import('./components/monitoring/tabprincipal/TabsDiagnosisATM')
            },{
                path: '/view/setting/atm',
                name: 'atmsetting',
                index: 2,
                component: () => import('./components/monitoring/tabprincipal/TabsConfigurationATM')
            }
            ]
        },
    ]

})
import NProgress from 'nprogress';

router.beforeResolve((to, from, next) => {
    // If this isn't an initial page load.
    if (to.name) {
        // Start the route progress bar.
        NProgress.start()
    }
    next()
})

router.afterEach(() => {
    // Complete the animation of the route progress bar.
    NProgress.done()
})
export default router;
