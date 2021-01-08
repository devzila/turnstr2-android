package com.wdp.Interface;



import com.wdp.Agora.LiveBroadcast;
import com.wdp.Modal.CommetListDataModal;

import com.wdp.Modal.CreatePosts_model;
import com.wdp.Modal.CreateStroyRequest_model;
import com.wdp.Modal.FavouriteslistDataModal;
import com.wdp.Modal.CommanDataModal;
import com.wdp.Modal.FollowerDataModal;
import com.wdp.Modal.FollowingDataModal;
import com.wdp.Modal.ForgotpasswordDataModal;
import com.wdp.Modal.InviteDataModal;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Modal.MeStoryDataModal;
import com.wdp.Modal.MyPostDataModal;
import com.wdp.Modal.OtherProfileDataModal;
import com.wdp.Modal.PostCommentModal;
import com.wdp.Modal.PostDataModal;
import com.wdp.Modal.PostLikesDataModal;
import com.wdp.Modal.PostReportDataModal;
import com.wdp.Modal.ProfileDataModal;
import com.wdp.Modal.ProfileEditDataModal;
import com.wdp.Modal.SearchDataModal;
import com.wdp.Modal.SharingChannelModel;
import com.wdp.Modal.StoryDataModal;
import com.wdp.Modal.SufflePosts_model;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface APIInterface {




    @POST(ApiConstants.SIGNUP)
    @FormUrlEncoded
    Call<LoginResDataModal> signup(@Field("user[name]") String name,
                                   @Field("user[email]") String email,
                                   @Field("user[username]") String username,
                                   @Field("user[password]") String password,
                                   @Field("user[device_id]") String device_id,
                                   @Field("user[device_token]") String device_token);


    @POST(ApiConstants.LOGIN)
    @FormUrlEncoded
    Call<LoginResDataModal> login(
            @Field("email") String email,
            @Field("password") String password,
            @Field("device_id") String device_id,
            @Field("device_token") String device_token);

    @POST(ApiConstants.FORGOT_PASSWORD)
    Call<ForgotpasswordDataModal> forgotpassword(@Body ForgotpasswordDataModal model);


    @POST(ApiConstants.Facebook_LOGIN)
    @FormUrlEncoded
    Call<LoginResDataModal> facebook(
            @Field("fb_user_access_token") String fb_user_access_token,
            @Field("device_id") String device_id,
            @Field("device_token") String device_token
    );

    @GET(ApiConstants.POSTS_URL)
    Call<PostDataModal> posts(@Header(ApiConstants.ACCESSTOKEN) String header,
                              @Query("page") String page);



    @POST(ApiConstants.POSTSLIKES_URL)
    Call<PostLikesDataModal> createLikes(@Header(ApiConstants.ACCESSTOKEN) String header,@Path("postId") String postId);


    @DELETE(ApiConstants.POSTSLIKES_URL)
    Call<PostLikesDataModal> postdisLikes(@Header(ApiConstants.ACCESSTOKEN) String header,@Path("postId") String postId);

    @GET(ApiConstants.POSTSCOMMENTLIST_URL)
    Call<CommetListDataModal> getCommentList(@Header(ApiConstants.ACCESSTOKEN) String header,@Path("postId") String postId);

    @POST(ApiConstants.POSTSCOMMENTS_URL)//posts/56/comments
    @FormUrlEncoded
    Call<PostCommentModal> postComment(@Header(ApiConstants.ACCESSTOKEN) String header,@Path("postId") String postId, @Field("comment[comment]") String comment);


    @POST(ApiConstants.POSTSREPORT_URL)
    @FormUrlEncoded
    Call<PostReportDataModal> postReport(@Header(ApiConstants.ACCESSTOKEN) String header,
                                         @Path("id") String id,
                                         @Field("report[reason]") String reason,
                                         @Field("report[text]") String text);



    @GET
    Call<FollowerDataModal> getFollowerMember(@Url String url,
                                              @Header(ApiConstants.ACCESSTOKEN) String header,
                                              @Query("page") String page);

    @GET(ApiConstants.MEFOLLOWER)
    Call<FollowerDataModal> getFollower(@Header(ApiConstants.ACCESSTOKEN) String header,
                                        @Query("page") String page);


    @GET(ApiConstants.MEFOLLOWING)
    Call<FollowingDataModal> getFollowing(@Header(ApiConstants.ACCESSTOKEN) String header,
                                          @Query("page") String page);
    @POST
    Call<CommanDataModal> getFollow(@Header(ApiConstants.ACCESSTOKEN) String header,
                                    @Url String url);


    @DELETE
    Call<CommanDataModal> geUnFollow(@Header(ApiConstants.ACCESSTOKEN) String header,
                                     @Url String url);


    @GET(ApiConstants.MEE_STORIES)
    Call<MeStoryDataModal> getMeStory(@Header(ApiConstants.ACCESSTOKEN) String header);


    @GET(ApiConstants.STORIES)
    Call<StoryDataModal> getStory(@Header(ApiConstants.ACCESSTOKEN) String header);


    @GET(ApiConstants.PROFILE)//v1/me/profile
    Call<ProfileDataModal> get_Profile(@Header(ApiConstants.ACCESSTOKEN) String header);



    @GET(ApiConstants.ME_Favourites)//v1/me/profile
    Call<FavouriteslistDataModal> getFavourites(@Header(ApiConstants.ACCESSTOKEN) String header);


    @GET(ApiConstants.MY_POSTS)
    Call<MyPostDataModal> MyPosts(@Header(ApiConstants.ACCESSTOKEN) String header,@Query("page") String page);


    @GET
    Call<SearchDataModal> Search(@Url String url,
                                 @Header(ApiConstants.ACCESSTOKEN) String header,
                                 @Query("page") String page);


    @Multipart
    @PUT(ApiConstants.PROFILE)
    Call<ProfileEditDataModal> updateProfile(
            @Part("user[name]") RequestBody name,
            @Part("user[username]") RequestBody username,
            @Part("user[bio]") RequestBody bio,
            @Part("user[phone]") RequestBody phone,
            @Part("user[website]") RequestBody website,
            @Part MultipartBody.Part avatar1,
            @Part MultipartBody.Part avatar2,
            @Part MultipartBody.Part avatar3,
            @Part MultipartBody.Part avatar4,
            @Part MultipartBody.Part avatar5,
            @Part MultipartBody.Part avatar6,
            @Part MultipartBody.Part background,
            @Header("Authorization") String token);



    @Multipart
    @PUT(ApiConstants.PROFILE)
    Call<ProfileEditDataModal> updateProfile1(
            @Part("user[name]") RequestBody name,
            @Part("user[username]") RequestBody username,
            @Part("user[bio]") RequestBody bio,
            @Part("user[phone]") RequestBody phone,
            @Part("user[website]") RequestBody website,
            @Header("Authorization") String token);


    @Multipart
    @PUT(ApiConstants.PROFILE)
    Call<ProfileEditDataModal> updateProfile2(
            @Part("user[name]") RequestBody name,
            @Part("user[username]") RequestBody username,
            @Part("user[bio]") RequestBody bio,
            @Part("user[phone]") RequestBody phone,
            @Part("user[website]") RequestBody website,
            @Part MultipartBody.Part background,
            @Header("Authorization") String token);



    @GET("/v1/members/{memberId}/favourites")
    Call<FavouriteslistDataModal> getFavourites(@Header(ApiConstants.ACCESSTOKEN) String header,@Path("memberId") String postId);


    @GET("/v1/members/{userID}")
    Call<OtherProfileDataModal> getOtherProfile(@Header(ApiConstants.ACCESSTOKEN) String header,@Path("userID") String postId);

    @GET("/v1/members/{userID}/posts")
    Call<MyPostDataModal> getOtherUserpost(@Header(ApiConstants.ACCESSTOKEN) String header,
                                           @Path("userID") String userID,
                                            @Query("page") String page);


    @DELETE
    Call<CommanDataModal> getUnTurent(@Header(ApiConstants.ACCESSTOKEN) String header,
                                      @Url String url);


    @POST
    Call<CommanDataModal> getTurent(@Header(ApiConstants.ACCESSTOKEN) String header,
                                      @Url String url);



    @PUT
    Call<CommanDataModal> getFavouritesFive(@Header(ApiConstants.ACCESSTOKEN) String header,
                                    @Url String url);


    @DELETE
    Call<CommanDataModal> getFavouritesFive1(@Header(ApiConstants.ACCESSTOKEN) String header,
                                            @Url String url);
    @GET
    Call<SharingChannelModel> SharingChannelName(@Header(ApiConstants.ACCESSTOKEN) String header,
                                                 @Url String stateid);



    @POST(ApiConstants.ME_LIVE)
    Call<LiveBroadcast> getLiveBroadcast(@Header(ApiConstants.ACCESSTOKEN) String header,
                                         @Header("Content-Type") String content_type);

    @DELETE(ApiConstants.ME_LIVE)
    Call<LiveBroadcast> getRemoveLiveBroadcast(@Header(ApiConstants.ACCESSTOKEN) String header,
                                               @Header("Content-Type") String content_type);

    @GET
    Call<InviteDataModal> Invite(@Url String url,
                                 @Header(ApiConstants.ACCESSTOKEN) String header);


    @POST(ApiConstants.CREATE_POSTS)
    Call<PostDataModal> getStoryPostDataModal(@Body CreatePosts_model createPosts_model,
                                              @Header(ApiConstants.ACCESSTOKEN) String header,
                                              @Header("Content-Type") String content_type);


    @PUT
    Call<PostDataModal> getEditCaption(@Url String url,
                                             @Header(ApiConstants.ACCESSTOKEN) String header,
                                             @Body CreatePosts_model createPosts_model);



    @POST(ApiConstants.ME_CREATE_STORIES)
    Call<CommanDataModal> meCreateStories(@Body CreateStroyRequest_model createPosts_model,
                                        @Header(ApiConstants.ACCESSTOKEN) String header);

    @GET
    Call<FollowingDataModal> getFollowingFollowingMember(@Url String url,
                                                         @Header(ApiConstants.ACCESSTOKEN) String header,
                                                         @Query("page") String page);


    @DELETE
    Call<CommanDataModal> deletePost(@Url String url,
                                     @Header(ApiConstants.ACCESSTOKEN) String header);


    @POST
    Call<CommanDataModal> getSuffleData(@Url String url,
                                            @Body SufflePosts_model.Post post,
                                            @Header(ApiConstants.ACCESSTOKEN) String header);

    /* @FormUrlEncoded
    @POST(ApiConstants.LOGIN_URL)
    Call<LoginRegModal> getLogindata(@Field(ApiConstants.EMAIL) String email,
                                     @Field(ApiConstants.PASSWORD) String password,
                                     @Field(ApiConstants.DEVICEID) String deviceID,
                                     @Field(ApiConstants.DEVICE_TYPE) String deviceType);

   @FormUrlEncoded
    @POST(ApiConstants.REGISTER_URL)
    Call<RegisterRegModal> getRegisterdata(@Field(ApiConstants.USERNAME) String username,
                                           @Field(ApiConstants.EMAIL) String email,
                                           @Field(ApiConstants.PASSWORD) String password,
                                           @Field(ApiConstants.CONFIRMPASSWORD) String confirm_password,
                                           @Field(ApiConstants.DEVICEID) String deviceID);


    @FormUrlEncoded
    @POST(ApiConstants.UPATE_PASSWORD_URL)
    Call<PasswordResetBean> UserPasswordResetData(@Field(ApiConstants.PASSWORD) String password,
                                                  @Field(ApiConstants.CONFIRMPASSWORD) String confirm_password,
                                                  @Field(ApiConstants.PASSWORD_STRING) String password_string);

    @FormUrlEncoded
    @POST(ApiConstants.CHANGEPASSWORD_URL)
    Call<ChangePasswordRegBean> getChangePassworddata(@Field(ApiConstants.USERID) String userid,
                                                      @Field(ApiConstants.OLD_PASSWORD) String old_password,
                                                      @Field(ApiConstants.NEW_PASSWORD) String new_password,
                                                      @Field(ApiConstants.CONFIRMPASSWORD) String conf_password,
                                                      @Header(ApiConstants.ACCESSTOKEN) String header);



    @GET(ApiConstants.COUNTRY_URL)
    Call<CountryRegModal> getCountrydata(@Header(ApiConstants.ACCESSTOKEN) String header);


    @GET
    Call<StateRegModel> getStatedata(@Header(ApiConstants.ACCESSTOKEN) String header,
                                     @Url String countryid);

    @GET
    Call<CityRegModel> getCitydata(@Header(ApiConstants.ACCESSTOKEN) String header,
                                   @Url String stateid);




    @FormUrlEncoded
    @POST(ApiConstants.LOGOUT_URL)
    Call<LogoutRegModel> getLogoutdata(@Field(ApiConstants.EMAIL) String email,
                                       @Header(ApiConstants.ACCESSTOKEN) String header);


    @Multipart
    @POST(ApiConstants.COMMENT_URL)
    Call<PostCommentRegModel> getPostCommentListdata(@Part(ApiConstants.CATEGORY_ID) RequestBody category_id,
                                                     @Part(ApiConstants.USERID) RequestBody user_id,
                                                     @Part(ApiConstants.COMMENT) RequestBody comment,
                                                     @Header(ApiConstants.ACCESSTOKEN) String header,
                                                     @Part(ApiConstants.DOCUMENT_TYPE) RequestBody fileType,
                                                     @Part MultipartBody.Part image);



    @Multipart
    @POST(ApiConstants.COMMENT_ON_COMMENT_URL)
    Call<CommentOnComment> getCommentReplayList(@Part(ApiConstants.CATEGORY_ID) RequestBody category_id,
                                                @Part(ApiConstants.USERID) RequestBody user_id,
                                                @Part(ApiConstants.COMMENT) RequestBody comment,
                                                @Header(ApiConstants.ACCESSTOKEN) String header,
                                                @Part(ApiConstants.DOCUMENT_TYPE) RequestBody fileType,
                                                @Part(ApiConstants.TOUSERID) RequestBody to_user_id,
                                                @Part(ApiConstants.COMMENT_ID) RequestBody comment_id,
                                                @Part MultipartBody.Part image);


    @GET
    Call<UserProfileRegModel> getUserProfiledata(@Header(ApiConstants.ACCESSTOKEN) String header,
                                                 @Url String userid);


    @GET(ApiConstants.CATEGORY_LIST_URL)
    Call<CategoryListModal> getCategorydata(@Header(ApiConstants.ACCESSTOKEN) String header);


    @GET(ApiConstants.ABOUT_OBAFA_URL)
    Call<AboutObafaModal> getAboutObafa(@Header(ApiConstants.ACCESSTOKEN) String header);

    @FormUrlEncoded
    @POST(ApiConstants.ADS_OBAFA_URL)
    Call<AdssDataModal> getAds(@Header(ApiConstants.ACCESSTOKEN) String header,
                               @Field(ApiConstants.STATUS) String staus);



    @FormUrlEncoded
    @POST(ApiConstants.EDIT_PROFILE_URL)
    Call<LoginRegModal> getEditProfiledata(@Field(ApiConstants.USERID) String userid,
                                           @Field(ApiConstants.USERNAME) String user_name,
                                           @Field(ApiConstants.EMAIL) String email,
                                           @Field(ApiConstants.COUNTRY_ID) String country_id,
                                           @Field(ApiConstants.STATE_ID) String state_id,
                                           @Field(ApiConstants.CITY_ID) String city_id,
                                           @Field(ApiConstants.DESCRIPTION) String description,
                                           @Header(ApiConstants.ACCESSTOKEN) String header);



    @Multipart
    @POST(ApiConstants.EDIT_PROFILE_URL)
    Call<LoginRegModal> getEditProfilewithImagedata(@Part(ApiConstants.USERID) RequestBody userid,
                                                    @Part(ApiConstants.USERNAME) RequestBody user_name,
                                                    @Part(ApiConstants.EMAIL) RequestBody email,
                                                    @Part(ApiConstants.COUNTRY_ID) RequestBody country_id,
                                                    @Part(ApiConstants.STATE_ID) RequestBody state_id,
                                                    @Part(ApiConstants.CITY_ID) RequestBody city_id,
                                                    @Part(ApiConstants.DESCRIPTION) RequestBody description,
                                                    @Header(ApiConstants.ACCESSTOKEN) String header,
                                                    @Part MultipartBody.Part image);


    @FormUrlEncoded
    @POST(ApiConstants.INTRECT_PEOPLE_URL)
    Call<IntreactPeopleModel> getIntrectPeople(@Field(ApiConstants.NAME) String name,
                                               @Header(ApiConstants.ACCESSTOKEN) String header);

    @FormUrlEncoded
    @POST(ApiConstants.UPDATE_NOTIFICATION_URL)
    Call<UpdateNotificationRegModel> getUpdateNotificationdata(@Field(ApiConstants.USERID) String userid,
                                                               @Field(ApiConstants.NOTIFICATION) String notification,
                                                               @Header(ApiConstants.ACCESSTOKEN) String header);


    @FormUrlEncoded
    @POST(ApiConstants.FORGET_PASSWORD_URL)
    Call<ForgetPasswordRegModel> getForgetPassworddata(@Field(ApiConstants.EMAIL) String email);


    @FormUrlEncoded
    @POST(ApiConstants.CHAT_CONVERSION_URL)
    Call<ChatConversionRegModel> getChatConversiondata(@Field(ApiConstants.SENDER_ID) String sender_id,
                                                       @Field(ApiConstants.RECEIVER_ID) String receiver_id,
                                                       @Header(ApiConstants.ACCESSTOKEN) String header);


    @FormUrlEncoded
    @POST(ApiConstants.SHOW_COMMENT_URL)
    Call<ShowCommentRegModel> getshowCommentdata(@Field(ApiConstants.CATEGORY_ID) String category_id,
                                                 @Field(ApiConstants.USERID) String userid,
                                                 @Header(ApiConstants.ACCESSTOKEN) String header);

    @FormUrlEncoded
    @POST(ApiConstants.COMMENT_LIST_URL)
    Call<CommentReplayListDataModal> getCommentReplaylist(@Field(ApiConstants.CATEGORY_ID) String category_id,
                                                          @Field(ApiConstants.USERID) String userid,
                                                          @Header(ApiConstants.ACCESSTOKEN) String header,
                                                          @Field(ApiConstants.COMMENT_ID) String comment_id);



    @FormUrlEncoded
    @POST(ApiConstants.LIKE_DISLIKE_URL)
    Call<LikeDislikeRegModel> getLikeDislikedata(@Field(ApiConstants.USERID) String userid,
                                                 @Field(ApiConstants.CATEGORY_ID) String category_id,
                                                 @Field(ApiConstants.COMMENT_USER_ID) String comment_user_id,
                                                 @Field(ApiConstants.TYPE) String type,
                                                 @Header(ApiConstants.ACCESSTOKEN) String header);



    @FormUrlEncoded
    @POST(ApiConstants.SEND_MEASSAGE_URL)
    Call<SendMeassageRegModel> getSendMeassagedata(@Field(ApiConstants.SENDER_ID) String sender_id,
                                                   @Field(ApiConstants.RECEIVER_ID) String receiver_id,
                                                   @Field(ApiConstants.MESSAGE) String message,
                                                   @Header(ApiConstants.ACCESSTOKEN) String header);



    @FormUrlEncoded
    @POST(ApiConstants.CHAT_LIST_URL)
    Call<ChatListRegModel> getChatListdata(@Field(ApiConstants.SENDER_ID) String sender_id,
                                           @Header(ApiConstants.ACCESSTOKEN) String header);






    @FormUrlEncoded
    @POST(ApiConstants.SOCIAL_LOGIN)
    Call<LoginRegModal> getSocialLoginData(@Field(ApiConstants.USER_NAME) String user_name,
                                           @Field(ApiConstants.EMAIL) String email,
                                           @Field(ApiConstants.DEVICEID) String device_id,
                                           @Field(ApiConstants.PROVIDER_ID) String provider_id,
                                           @Field(ApiConstants.DEVICE_TYPE) String device_type);



    @FormUrlEncoded
    @POST(ApiConstants.DELETE_COMMENT_URL)
    Call<DeleteCommentModel> deleteComment(
            @Field(ApiConstants.COMMENT_ID) String comment_id,
            @Header(ApiConstants.ACCESSTOKEN) String header
    );


    @FormUrlEncoded
    @POST(ApiConstants.DELETE_ACCOUNT_TAG)
    Call<DeleteCommentModel> deleteAccount(
            @Field(ApiConstants.USERID) String comment_id,
            @Header(ApiConstants.ACCESSTOKEN) String header
    );

    @FormUrlEncoded
    @POST(ApiConstants.USER_LIKE_URL)
    Call<UserLikesDataModal> getUserLikes(
            @Field(ApiConstants.USERID) String comment_id,
            @Field(ApiConstants.TOUSERID) String to_user_id,
            @Header(ApiConstants.ACCESSTOKEN) String header
    );

    @FormUrlEncoded
    @POST(ApiConstants.USER_LIKELISTING_URL)
    Call<UserLikeListingDataModal> getUserLikesListing(
            @Field(ApiConstants.USERID) String comment_id,
            @Header(ApiConstants.ACCESSTOKEN) String header
    );

    @FormUrlEncoded
    @POST(ApiConstants.REGISTER_URL)
    Call<RegisterRegModal> getPushNotification(@Field(ApiConstants.USERNAME) String username,

                                               @Field(ApiConstants.DEVICEID) String deviceID);*/



}



