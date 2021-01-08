package com.wdp.Interface;

/**
 * Created by admin on 9/7/2018.
 */

public class ApiConstants {
    public static final String BASE_URL = "http://apiv2.turnstr.com/v1/";
    public static final String LOGIN = "auth/login";
    public static final String Facebook_LOGIN = "/v1/facebook";
    public static final String SIGNUP = "signup";
    public static final String FORGOT_PASSWORD = "forgotpassword";
    public static final String POSTS_URL = "posts";
    public static final String POSTSLIKES_URL = "posts/{postId}/likes";
    public static final String POSTSCOMMENTLIST_URL = "posts/{postId}/comments";
    public static final String POSTSCOMMENTS_URL = "posts/{postId}/comments";
    public static final String POSTSREPORT_URL = "posts/{id}/report";
    public static final String MEFOLLOWING = "me/following";
    public static final String MEFOLLOWER = "me/followers";
    public static final String Member_Follow = "members/id/follow";
    public static final String MEE_STORIES = "me/stories";
    public static final String STORIES = "stories";
    public static final String PROFILE = "me/profile";
    public static final String MY_POSTS = "me/posts";
    public static final String Search = "search/";
    public static final String ME_Favourites= "me/favourites";
    public static final String Me_Favourites= "members/id/follow";
    public static final String Favourites_Five= "me/favourites/";
    public static final String ME_LIVE = "me/live";
    public static final String CREATE_POSTS = "me/posts";
    public static final String ME_CREATE_STORIES = "me/stories";
    public static final String MEMBEFOLLOWER = "members/(id)/followers";
    public static final String MEMBEFOLLWING = "members/(id)/following";
    public static final String ME_POST_DELETE = "me/posts/";
    public static final String ME_POSTS = "me/posts/";
    public static final String ME_SHUFFLEMEDIAS = "me/posts/(postId)/shuffle_medias";

    public static String sharing_channel="sharingChannel";
    public static final String DELETE_ACCOUNT_TAG="delete-account";
    public static final String DELETE_COMMENT_TAG="delete-comment";
    public static final String COMMENT_TAG = "commentreplay";
    public static final String COMMENT_ON_COMMENT_TAG = "comment_on_comment";
    public static final String COMMENTREPLAY_TAG = "comment";
    public static final String LOGIN_TAG = "login";
    public static final String GETCOUNTRY_TAG = "country";
    public static final String REGISTER_TAG = "register";
    public static final String CHANGEPASSWORD_TAG ="change-password";
    public static final String STATE_TAG ="state";
    public static final String CITY_TAG ="city";
    public static final String LOGOUT_TAG ="logout";
    public static final String NOTIFICATION_LIST_TAG = "userNotifications";
    public static final String USER_PROFILE_TAG ="view-profile";
    public static final String EDIT_PROFILE_TAG ="update-account";
    public static final String SEND_MEASSAGE_TAG="message";
    public static final String CHAT_LIST_TAG="messages-user";
    public static final String FORGET_PASSWORD_TAG="forgot-password";
    public static final String UPDATE_NOTIFICATION_TAG ="update-notification";
    public static final String PRIVACY_POLICY_URL ="http://obafa.com/privacy-policy";
    public static final String TERMS_AND_CONDITION_URL ="http://obafa.com/terms-and-conditions";

    public static final String LIKE_DISLIKE_TAG= "like-dislike";
    public static final String ADD_EDIT_HOTSPOT_TAG="addEditHotspot";
    public static final String UPATE_PASSWORD_TAG ="update-password ";
    public static final String CATEGORY_LIST_TAG="category";
    public static final String GET_DISTANCE_RANGE_TAG ="getDistanceRange";
    public static final String CHAT_CONVERSION_TAG="chat-conversion";
    public static final String READ_NOTIFICATION_TAG="readNotifications";
    public static final String ABOUT_OBAFA_TAG="about-obafa";
    public static final String ADS_TAG="getAds";
    public static final String INTRECT_PEOPLE_TAG="intreact-people";
    public static final String SHOW_COMMENT_TAG="show-comment";
    public static final String USER_LIKE_TAG="user-like";
    public static final String USER_LIKELISTING_TAG="like-listing";

    // PARAMETER
    public static final String USERNAME="user_name";
    public static final String ACCESSTOKEN="Authorization";
    public static final String PASSWORD="password";
    public static final String DEVICEID ="device_id";
    public static final String DEVICE_TYPE="device_type";
    public static final String PROVIDER_ID="provider_id";
    public static final String NEW_PASSWORD="password";
    public static final String OLD_PASSWORD="old_password";
    public static final String SENDER_ID="sender_id";
    public static final String TYPE="type";
    public static final String USER_NAME="user_name";
    public static final String CATEGORY_ID="category_id";
     public static final String EMAIL="email";
    public static final String COUNTRY_ID="country_id";
    public static final String STATUS="status";
    public static final String COMMENT_USER_ID="comment_id";
    public static final String USERID ="user_id";
    public static final String TOUSERID ="to_user_id";
    public static final String COMMENT ="comment";
    public static final String COMMENT_ID ="comment_id";
    public static final String DOCUMENT_TYPE ="document_type";
    public static final String STATE_ID ="state_id";
    public static final String Mobile="mobile";
    public static final String CITY_ID="city_id";
    public static final String NOTIFICATION="notification";
    public static final String NAME="name";
    public static final String DESCRIPTION="description";
    public static final String VALUE="value";
    public static final String RECEIVER_ID="receiver_id";

    public static final String MESSAGE="message";
    public static final String CITY_NAME="city_name";
    public static final String STATE_NAME="state_name";
    public static final String IMAGE="image";
    public static final String FILE="file";
    public static final String MOBILE_NUMBER="mobile_number";
    public static final String ADDRESS="address";
    public static final String PROFESSION="profession";
    public static final String GENDER="gender";
    public static final String FIRSTNAME="first_name";
    public static final String LASTNAME="last_name";
    public static final String CONFIRMPASSWORD="confirm_password";
    public static final String PASSWORD_STRING="password_string";
    public static final String LAT="lat";
    public static final String LNG="lng";
    public static final String DATE_TIME="date_time";
    //Comman Tag
    public static String signup_tag="signup_tag";
    public static String login_tag="login";
    public static String forgotpassowrd_tag="forgotpassowrd";
    public static String posts_tag="posts_tag";
    public static String mestoryview_tag="me_storyview";
    public static String posts_likes_tag="posts_likes";
    public static String posts_commentlist_tag="posts_comment_list";
    public static String posts_comment_tag="posts_comment";
    public static String posts_reason_tag="posts_reason";
    public static String me_following="me-following";
    public static String me_follower="me-follower";
    public static String FollowUnFollow_TAG="FollowUnFollow";
    public static String storty_TAG="story";
    public static String profile_TAG="me_profile";
    public static String other_profile_TAG="other_me_profile";
    public static String my_post_TAG="meposts";
    public static String search_view_TAG="search";
    public static String profileedit_TAG="profile_tg";
    public static String favourites_TAG="me_favourites_tag";
    public static String turn_unturn_TAG="turn_unturn";
    public static String favfive_TAG="favfive";
    public static String StoryPost_TAG="StoryPost";
    public static String MePostdelete_TAG="MePostDelete";
    public static String SuffleMedia_TAG="SuffleMedia";
    public static final String ME_POSTS_Tag = "Post";
    public static String NotificationRemove_API_TAG="notificationRemove";
    public static String IsTermsAndCondition_API_TAG="isTermsAndCondition";
    public static String LogOut_API_TAG="logout";
    public static String appID = "21836b1312ac88c";
    public static String AuthKey = "2f715a967d1f0b5d1c270e9697e8a81121bf5e80";
    public static String region = "EU";






}
