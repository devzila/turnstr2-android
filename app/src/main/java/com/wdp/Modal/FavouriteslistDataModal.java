package com.wdp.Modal;

import com.wdp.Interface.SuperCastClass;

import java.util.List;

public class FavouriteslistDataModal extends SuperCastClass {

    /**
     * success : true
     * message : null
     * data : {"users":[{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/new-1.png?1607062930","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/new-1.png?1607062930","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/new-1.png?1607062930"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/logo-1-1.png?1607062931","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/logo-1-1.png?1607062931","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/logo-1-1.png?1607062931"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/IMG_20201127_104225.jpg?1607062931","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/IMG_20201127_104225.jpg?1607062931","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/IMG_20201127_104225.jpg?1607062931"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/IMG_20201115_124946.jpg?1607062932","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/IMG_20201115_124946.jpg?1607062932","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/IMG_20201115_124946.jpg?1607062932"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-04T06:22:15.929Z"}]}
     */

    private String success;
    private String message;
    private DataBean data;

    public String isSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<UsersBean> users;

        public List<UsersBean> getUsers() {
            return users;
        }

        public void setUsers(List<UsersBean> users) {
            this.users = users;
        }

        public static class UsersBean {
            /**
             * id : 14
             * username : vicky123
             * name : Vicky
             * active : true
             * avatar : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/new-1.png?1607062930","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/new-1.png?1607062930","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/new-1.png?1607062930"}
             * avatar2 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/logo-1-1.png?1607062931","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/logo-1-1.png?1607062931","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/logo-1-1.png?1607062931"}
             * avatar3 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/IMG_20201127_104225.jpg?1607062931","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/IMG_20201127_104225.jpg?1607062931","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/IMG_20201127_104225.jpg?1607062931"}
             * avatar4 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/IMG_20201115_124946.jpg?1607062932","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/IMG_20201115_124946.jpg?1607062932","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/IMG_20201115_124946.jpg?1607062932"}
             * avatar5 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"}
             * avatar6 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"}
             * background : null
             * followed_by_current_user : false
             * is_broadcasting : false
             * is_private : false
             * verified : false
             * created_at : 2020-11-23T12:47:17.386Z
             * updated_at : 2020-12-04T06:22:15.929Z
             */

            private String id;
            private String username;
            private String name;
            private String active;
            private AvatarBean avatar;
            private Avatar2Bean avatar2;
            private Avatar3Bean avatar3;
            private Avatar4Bean avatar4;
            private Avatar5Bean avatar5;
            private Avatar6Bean avatar6;
            private BackgroundBean background;
            private String followed_by_current_user;
            private String is_broadcasting;
            private String is_private;
            private String verified;
            private String created_at;
            private String updated_at;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String isActive() {
                return active;
            }

            public void setActive(String active) {
                this.active = active;
            }

            public AvatarBean getAvatar() {
                return avatar;
            }

            public void setAvatar(AvatarBean avatar) {
                this.avatar = avatar;
            }

            public Avatar2Bean getAvatar2() {
                return avatar2;
            }

            public void setAvatar2(Avatar2Bean avatar2) {
                this.avatar2 = avatar2;
            }

            public Avatar3Bean getAvatar3() {
                return avatar3;
            }

            public void setAvatar3(Avatar3Bean avatar3) {
                this.avatar3 = avatar3;
            }

            public Avatar4Bean getAvatar4() {
                return avatar4;
            }

            public void setAvatar4(Avatar4Bean avatar4) {
                this.avatar4 = avatar4;
            }

            public Avatar5Bean getAvatar5() {
                return avatar5;
            }

            public void setAvatar5(Avatar5Bean avatar5) {
                this.avatar5 = avatar5;
            }

            public Avatar6Bean getAvatar6() {
                return avatar6;
            }

            public void setAvatar6(Avatar6Bean avatar6) {
                this.avatar6 = avatar6;
            }

            public BackgroundBean getBackground() {
                return background;
            }

            public void setBackground(BackgroundBean background) {
                this.background = background;
            }

            public String isFollowed_by_current_user() {
                return followed_by_current_user;
            }

            public void setFollowed_by_current_user(String followed_by_current_user) {
                this.followed_by_current_user = followed_by_current_user;
            }

            public String isIs_broadcasting() {
                return is_broadcasting;
            }

            public void setIs_broadcasting(String is_broadcasting) {
                this.is_broadcasting = is_broadcasting;
            }

            public String isIs_private() {
                return is_private;
            }

            public void setIs_private(String is_private) {
                this.is_private = is_private;
            }

            public String isVerified() {
                return verified;
            }

            public void setVerified(String verified) {
                this.verified = verified;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public static class BackgroundBean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609140852
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609140852
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609140852
                 */

                private String thumb;
                private String medium;
                private String original;

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }
            }

            public static class AvatarBean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/new-1.png?1607062930
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/new-1.png?1607062930
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/new-1.png?1607062930
                 */

                private String thumb;
                private String medium;
                private String original;

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }
            }

            public static class Avatar2Bean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/logo-1-1.png?1607062931
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/logo-1-1.png?1607062931
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/logo-1-1.png?1607062931
                 */

                private String thumb;
                private String medium;
                private String original;

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }
            }

            public static class Avatar3Bean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/IMG_20201127_104225.jpg?1607062931
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/IMG_20201127_104225.jpg?1607062931
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/IMG_20201127_104225.jpg?1607062931
                 */

                private String thumb;
                private String medium;
                private String original;

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }
            }

            public static class Avatar4Bean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/IMG_20201115_124946.jpg?1607062932
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/IMG_20201115_124946.jpg?1607062932
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/IMG_20201115_124946.jpg?1607062932
                 */

                private String thumb;
                private String medium;
                private String original;

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }
            }

            public static class Avatar5Bean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933
                 */

                private String thumb;
                private String medium;
                private String original;

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }
            }

            public static class Avatar6Bean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934
                 */

                private String thumb;
                private String medium;
                private String original;

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }
            }
        }
    }
}
