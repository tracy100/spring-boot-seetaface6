-- 创建向量索引，向量搜索更快
CREATE INDEX idx_seeta_face_info_features_hnsw ON public.seeta_face_info USING hnsw (features vector_cosine_ops) WITH (m = 32, ef_construction = 200);