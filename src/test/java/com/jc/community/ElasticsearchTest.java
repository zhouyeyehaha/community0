//package com.jc.community;
//
//import com.jc.community.dao.DiscussPostMapper;
//import com.jc.community.dao.elasticsearch.DiscussPostRepository;
//import com.jc.community.entity.DiscussPost;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.elasticsearch.search.sort.SortBuilders;
//import org.elasticsearch.search.sort.SortOrder;
//import org.junit.jupiter.api.Test;
////import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//
//@SpringBootTest
//public class ElasticsearchTest {
//
//    @Autowired
//    private DiscussPostMapper discussMapper;
//
//    @Autowired
//    private DiscussPostRepository discussRepository;
//
////    @Autowired
////    private ElasticsearchTemplate elasticsearchTemplate;
//
//    @Test
//    public void test01() {
//
//        discussRepository.save(discussMapper.selectDiscussPostById(231));
//        discussRepository.save(discussMapper.selectDiscussPostById(232));
//        discussRepository.save(discussMapper.selectDiscussPostById(233));
//    }
//
//    @Test
//    public void testInsertList() {
//        discussRepository.saveAll(discussMapper.selectDiscussPosts(101, 0, 100));
//        discussRepository.saveAll(discussMapper.selectDiscussPosts(102, 0, 100));
//        discussRepository.saveAll(discussMapper.selectDiscussPosts(103, 0, 100));
//        discussRepository.saveAll(discussMapper.selectDiscussPosts(111, 0, 100));
//        discussRepository.saveAll(discussMapper.selectDiscussPosts(112, 0, 100));
//        discussRepository.saveAll(discussMapper.selectDiscussPosts(131, 0, 100));
//        discussRepository.saveAll(discussMapper.selectDiscussPosts(132, 0, 100));
//        discussRepository.saveAll(discussMapper.selectDiscussPosts(133, 0, 100));
//        discussRepository.saveAll(discussMapper.selectDiscussPosts(134, 0, 100));
//    }
//
//    @Test
//    public void testSearchByRepository() {
//        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//        nativeSearchQueryBuilder
//                .withQuery(QueryBuilders.multiMatchQuery("互联网寒冬", "title", "content"))
//                .withSort(SortBuilders.fieldSort("type").order(SortOrder.DESC))
//                .withSort(SortBuilders.fieldSort("score").order(SortOrder.DESC))
//                .withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
//                .withPageable(PageRequest.of(0, 10))
//                .withHighlightFields(
//                        new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>"),
//                        new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
//                ).build();
//
//        // elasticTemplate.queryForPage(searchQuery, class, SearchResultMapper)
//        // 底层获取得到了高亮显示的值, 但是没有返回.
//
//        Page<DiscussPost> page = discussRepository.searchSimilar(nativeSearchQueryBuilder);
//        System.out.println(page.getTotalElements());
//        System.out.println(page.getTotalPages());
//        System.out.println(page.getNumber());
//        System.out.println(page.getSize());
//        for (DiscussPost post : page) {
//            System.out.println(post);
//        }
//    }
//}
