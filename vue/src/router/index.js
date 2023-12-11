import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import GalleryView from '../views/GalleryView.vue';
import UserView from '../views/UserView.vue';
import PhotoUploadView from '../views/PhotoUploadView.vue';
import PhotoDetailView from '../views/PhotoDetailView.vue';
import FavoritesView from '../views/FavoritesView.vue';
import FollowingView from '../views/FollowingView.vue';

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/users/:id/photos",
    // path: "/gallery",
    name: "gallery",
    component: GalleryView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/user/{id}',
    name: 'user',
    component: UserView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/photos/:id',
    name: 'photo-detail',
    component: PhotoDetailView,
    meta: {
      requiresAuth: true
    }
  },


  {
    path: "/photoupload",
    name: "photoupload",
    component: PhotoUploadView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/{userId}/favorites",
    name: "favorites-page",
    component: FavoritesView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/following/{followerUserId}/photos",
    name: "following-page",
    component: FollowingView,
    meta: {
      requiresAuth: true
    }
  }

];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return { name: "login" };
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
