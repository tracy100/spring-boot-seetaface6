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

    @Update("SET pgvector.ef_search = 200")
    void setEfSearch();

    @Select("SELECT *, (features <-> #{targetFeatures}) as score FROM public.seeta_face_info " +
            "ORDER BY features <-> #{targetFeatures} " +
            "LIMIT ${topN}")
    List<FaceInfoBo> queryByFeatures(@Param("targetFeatures") float[] targetFeatures, @Param("topN") int topN);

}
