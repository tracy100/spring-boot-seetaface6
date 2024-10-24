package com.lyc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyc.entities.FaceInfo;
import com.lyc.entities.FaceInfoBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FaceInfoMapper extends BaseMapper<FaceInfo> {

    /**
     * 大模型回答：
     * 当你在每次查询之前执行 SET pgvector.ef_search = 200; 这条 SQL 语句时，你是在设置一个会话级别的参数，该参数会影响随后执行的向量相似度搜索的性能和准确性。以下是这条语句的具体作用：
     * 1.提高查询准确性：通过将 ef_search 设置为较高的值（如 200），你可以让查询算法检查更多的候选点，从而提高找到最相似向量的准确性。
     * 2.影响查询性能：虽然更高的 ef_search 值可以提高准确性，但它也会增加查询时间，因为需要处理更多的候选点。因此，你需要在这两者之间找到一个平衡点。
     * 3.会话级别设置：SET 语句是会话级别的，这意味着它只对当前会话有效。如果你有多个并发查询，每个查询都可以独立设置不同的 ef_search 值。
     */
    @Update("SET pgvector.ef_search = 200")
    void setEfSearch();

    /**
     * 向量余弦相似度搜索
     * @param targetFeatures 输入的向量
     * @param topN 返回最接近的前N个
     * @return list
     */
    @Select("SELECT *, (1 - (features <=> #{targetFeatures})) as score FROM public.seeta_face_info " +
            " ORDER BY features <=> #{targetFeatures} " +
            "LIMIT ${topN}")
    List<FaceInfoBo> queryByFeatures(@Param("targetFeatures") float[] targetFeatures, @Param("topN") int topN);


}
