package ru.kpfu.itis.group.kadyrov.servlet;

import ru.kpfu.itis.group.kadyrov.Helper;
import ru.kpfu.itis.group.kadyrov.services.*;
import ru.kpfu.itis.group.kadyrov.services.implementations.*;
import ru.kpfu.itis.group.kadyrov.utils.DataBaseColumns;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Амир on 15.11.2016.
 */
@WebServlet(name = "AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String table = "";
        //TODO
        /*if (request.getParameter("user_id") != null) {
            table = "users";
            UserService userService = new UserServiceImpl();
            userService.deleteUser(request.getParameter("user_id"));
        }*/
        if (request.getParameter("comment_news") != null) {
            table = "comment_news";
            NewsCommentService newsCommentService = new NewsCommentServiceImpl();
            newsCommentService.deleteNewsComment(Integer.parseInt(request.getParameter("comment_news")));
        }
        if (request.getParameter("game_id") != null) {
            table = "games";
            GameService gameService = new GameServiceImpl();
            gameService.deleteGame(Integer.parseInt(request.getParameter("game_id")));
        }
        if (request.getParameter("news_id") != null) {
            table = "news";
            NewsService newsService = new NewsServiceImpl();
            newsService.deleteNews(Integer.parseInt(request.getParameter("news_id")));
        }
        if (request.getParameter("comment_reviews") != null) {
            table = "comment_reviews";
            ReviewCommentService reviewCommentService = new ReviewCommentServiceImpl();
            reviewCommentService.deleteReviewComment(Integer.parseInt(request.getParameter("comment_reviews")));
        }
        if (request.getParameter("comment_videos") != null) {
            table = "comment_videos";
            VideoCommentService videoCommentService = new VideoCommentServiceImpl();
            videoCommentService.deleteVideoComment(Integer.parseInt(request.getParameter("comment_videos")));
        }
        if (request.getParameter("topic_id") != null) {
            table = "topics";
            TopicService topicService = new TopicServiceImpl();
            topicService.deleteTopic(Integer.parseInt(request.getParameter("topic_id")));
        }
        if (request.getParameter("video_id") != null) {
            table = "videos";
            VideoService videoService = new VideoServiceImpl();
            videoService.deleteVideo(Integer.parseInt(request.getParameter("video_id")));
        }
        if (request.getParameter("message_id") != null) {
            table = "messages";
            TopicMessageService topicMessageService = new TopicMessageServiceImpl();
            topicMessageService.deleteTopicMessage(Integer.parseInt(request.getParameter("message_id")));
        }
        if (request.getParameter("token") != null) {
            table = "cookies";
            TokenService tokenService = new TokenServiceImpl();
            tokenService.deleteToken(request.getParameter("token"));
        }
        if (request.getParameter("group_id") != null) {
            table = "groups";
            GroupService groupService = new GroupServiceImpl();
            groupService.deleteGroup(Integer.parseInt(request.getParameter("group_id")));
        }
        if (request.getParameter("review_id") != null) {
            table = "reviews";
            ReviewService reviewService = new ReviewServiceImpl();
            reviewService.deleteReview(Integer.parseInt(request.getParameter("review_id")));
        }
        response.sendRedirect("/admin?t=" + table);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> root = new HashMap<>();
        root.put("current_user", request.getSession().getAttribute("current_user"));

        String table = request.getParameter("t");

        if (table != null) {
            root.put("all_columns", DataBaseColumns.getColumns(request.getParameter("t")));

            if (table.equals("users")) {
                UserService userService = new UserServiceImpl();
                root.put("userService", userService);
                root.put("all_users", userService.getAllUsers());
            }
            if (table.equals("comment_news")) {
                NewsCommentService newsCommentService = new NewsCommentServiceImpl();
                root.put("all_news_comments", newsCommentService.getAllNewsComments());
            }
            if (table.equals("games")) {
                GameService gameService = new GameServiceImpl();
                root.put("all_games", gameService.getAllGames());
            }
            if (table.equals("news")) {
                NewsService newsService = new NewsServiceImpl();
                root.put("all_news", newsService.getAllNews());
            }
            if (table.equals("comment_reviews")) {
                ReviewCommentService reviewCommentService = new ReviewCommentServiceImpl();
                root.put("all_review_comments", reviewCommentService.getAllReviewComments());
            }
            if (table.equals("comment_videos")) {
                VideoCommentService videoCommentService = new VideoCommentServiceImpl();
                root.put("all_video_comments", videoCommentService.getAllVideoComments());
            }
            if (table.equals("topics")) {
                TopicService topicService = new TopicServiceImpl();
                root.put("all_topics", topicService.getAllTopics());
            }
            if (table.equals("videos")) {
                VideoService videoService = new VideoServiceImpl();
                root.put("all_videos", videoService.getAllVideos());
            }
            if (table.equals("messages")) {
                TopicMessageService topicMessageService = new TopicMessageServiceImpl();
                root.put("all_messages", topicMessageService.getAllMessages());
            }
            if (table.equals("cookies")) {
                TokenService tokenService = new TokenServiceImpl();
                root.put("all_cookies", tokenService.getAllTokens());
            }
            if (table.equals("groups")) {
                GroupService groupService = new GroupServiceImpl();
                root.put("all_groups", groupService.getAllGroups());
            }
            if (table.equals("reviews")) {
                ReviewService reviewService = new ReviewServiceImpl();
                root.put("all_reviews", reviewService.getAllReviews());
            }
        }

        Helper.render(request, response, "admin.ftl", root);

        root.clear();
    }
}
