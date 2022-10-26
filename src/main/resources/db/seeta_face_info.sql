-- ----------------------------
-- Table structure for seeta_face_info
-- ----------------------------

CREATE TABLE IF NOT EXISTS public.seeta_face_info (
    id int4 NOT NULL DEFAULT nextval('seeta_face_info_id_seq'::regclass),
    features vector(1024), -- 显式指定向量的维度
    file_name varchar(127),
    file_path varchar(256),
    x int4,
    y int4,
    width int4,
    height int4,
    create_time date,
    update_time date,
    points float8[][]
);

-- ----------------------------
-- Primary Key structure for table seeta_face_info
-- ----------------------------
ALTER TABLE "public"."seeta_face_info" ADD CONSTRAINT "seeta_face_info1_pkey" PRIMARY KEY ("id");

COMMENT ON COLUMN "public"."seeta_face_info"."id" IS '自增ID';
COMMENT ON COLUMN "public"."seeta_face_info"."features" IS '人脸向量';
COMMENT ON COLUMN "public"."seeta_face_info"."file_name" IS '原始文件名';
COMMENT ON COLUMN "public"."seeta_face_info"."file_path" IS '文件保存路径';
COMMENT ON COLUMN "public"."seeta_face_info"."x" IS '人脸在图片中的x轴位置';
COMMENT ON COLUMN "public"."seeta_face_info"."y" IS '人脸在图片中的y轴位置';
COMMENT ON COLUMN "public"."seeta_face_info"."width" IS '宽';
COMMENT ON COLUMN "public"."seeta_face_info"."height" IS '高';
COMMENT ON COLUMN "public"."seeta_face_info"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."seeta_face_info"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."seeta_face_info"."points" IS '人脸五点关键点';

-- ----------------------------
-- Records of seeta_face_info
-- ----------------------------
INSERT INTO "public"."seeta_face_info" ( "features", "file_name", "file_path", "x", "y", "width", "height", "create_time", "update_time", "points") VALUES ('[0.012936536,0,0.007195695,0.0010175805,0,0.007792639,0.015118308,0.011674433,0.009080521,0.014190655,0.044781953,0,0,0.02231579,0.0028997012,0.014974379,0.012804734,0.0037977195,0.0076714647,0.094358526,0.008663135,0.009487498,0.0055931387,0,0.018194415,0.008644766,0.086220555,0.009462898,0,0,0.0071884613,0.017078964,0.005971099,0.037262347,0,0.011930267,0.020462526,0.008314524,0.008506452,0,0.08382535,0.0043841833,0.01740133,0.012599094,0.024398023,0,0.022921681,0.035706557,0.007167895,0,0,0.01608521,0,0.014278957,0,0.05967452,0.005382457,0.009898098,0.028123062,0.10590437,0,0.018057222,0.016698858,0,0.0042912154,0.05017243,0,0.012554585,0,0.017572744,0.010788352,0.013510452,0,0.0068368926,0.028482487,0.03135618,0.010059538,0.013481353,0.008319363,0,0,0.003842533,0,0.015337106,0,0.012159346,0,0,0,0.02166239,0,0.016810121,0,0.002882563,0.014977325,0,0,0,0.028035989,0.054355,0.06605609,0.006695213,0.008298104,0.0070924135,0.010430695,0.052303135,0,0.013470027,0.012022528,0,0.051299147,0,0.058759604,0.010825306,0.018810216,0.009120669,0,0,0,0.021069858,0.06479335,0.010269384,0.022222854,0.0031150924,0,0.07087145,0.015606145,0.042596396,0.008097757,0.04202775,0.09486199,0.00927597,0,0.009832303,0.010240598,0.02286381,0.01631102,0.037719354,0.034105,0.09498593,0.010083296,0,0.03363972,0.091432266,0,0.0136626,0,0.060525417,0,0.0053605065,0.0141740525,0.053591624,0.022475224,0,0.044024613,0,0,0,0.047528785,0,0.041255392,0,0.026546773,0.016530082,0,0.013645481,0,0.018284744,0,0.049734954,0.013108354,0.0049782405,0.0034352227,0.054873597,0.005992258,0.027922483,0.062608026,0,0,0.009454261,0.063707955,0.08825763,0,0,0,0.0035791588,0.03670636,0.027578294,0.004111979,0.016055308,0.022207845,0.013669315,0.0184831,0.07337876,0.0060850573,0.04881681,0,0.0046898867,0,0.017978372,0,0.013703404,0.050636627,0.016924303,0.011133711,0.032126762,0.009875268,0,0.006539482,0.04149861,0.014229679,0.014637744,0.013330922,0.013252046,0.059673995,0,0,0.021640522,0.06237336,0.04687985,0,0.004836014,0.05147407,0.08285213,0,0.065025195,0.005084097,0.013403868,0,0.04430367,0.035835903,0,0,0.011138198,0,0.007573752,0.065894485,0,0.011623726,0.061509542,0.023587737,0.050479546,0.062357567,0.012624956,0.18149832,0,0.00991907,0.008969357,0.019379236,0.017703474,0.014122228,0.012409378,0.015835313,0.014909128,0.015797265,0.023986887,0.03605708,0,0.00484181,0.009798406,0,0.059107587,0.018749353,0.019966034,0.00075713336,0,0.009042693,0.04916493,0.024364747,0.0040479815,0.06552736,0.005386103,0.0149867395,0.009196071,0.059709586,0.110087544,0.03433313,0.004109531,0,0,0,0,0.03006751,0.04320631,0,0.04058286,0.023070406,0,0.003243711,0.02739622,0.0068308455,0,0.010815314,0.009077117,0.012960999,0.04238911,0.11240821,0.024731508,0.0069392575,0.01047473,0.086186536,0.006744124,0.06877251,0.027786484,0,0,0.01919137,0.035305697,0,0.014672417,0,0.010933243,0.014637537,0,0.010504993,0.010986123,0.011860298,0.014868989,0.023254147,0,0,0,0,0.026480256,0.0016338569,0.020332512,0.02371761,0.015045777,0,0,0,0,0,0.0053659203,0.011822672,0,0.0418138,0.00969026,0.013813995,0.017136646,0.011614178,0.06715224,0.012186076,0.004297241,0,0.08306359,0.030226767,0.008540841,0.03748476,0.01408638,0.076160826,0.011186378,0.0038412164,0.0055577685,0,0.008968809,0.0072584567,0,0.011027888,0.036231082,0,0.031956665,0.036360916,0.015415539,0.13346262,0,0,0.0083580855,0.023822602,0,0.027539948,0,0.01437315,0.023282068,0,0.00447503,0,0.011323216,0.0060033617,0.06053584,0,0.010618792,0,0.010148928,0.017687304,0,0.04178323,0.038190316,0,0.013922283,0,0.026840324,0.020681903,0.0040813996,0,0,0.0104454495,0.13480082,0.014522614,0,0.05382975,0.008104056,0.0048422986,0.043376617,0.0075269663,0,0.0074544055,0.015421718,0,0.007237645,0.011400381,0,0.0060491944,0.031341672,0.017193757,0,0,0.01113649,0.0035488072,0.01090996,0.014570169,0,0.028932387,0,0.01049893,0.010295641,0.052135974,0,0,0,0.08585738,0.009569165,0,0.016140802,0,0,0,0.01856908,0.010976821,0.008810176,0.006731395,0.015923157,0.01686836,0.012791781,0.010417162,0.015028711,0.006390597,0.018609561,0.060831644,0,0,0.00914265,0.026932271,0.014621711,0.0017739309,0,0.039525736,0.017013721,0.008868712,0.0037233206,0.007595041,0,0,0.07526842,0.012743788,0.00583687,0.012538236,0,0,0.0069439476,0.020589983,0.025325656,0.012636953,0.007814401,0.112313606,0.05582911,0.06509585,0,0.016695222,0,0.011220647,0.011739086,0.028447311,0,0.020543499,0.061481602,0,0.003007268,0.010969699,0,0.02220266,0.059171494,0.011578273,0,0.01423173,0,0.024742452,0,0.008418887,0.0099330945,0.008668409,0.0073191784,0,0,0,0,0.005542668,0.010724416,0.0012712771,0.08968355,0.0019512589,0.069424525,0.09707316,0.025636597,0,0.019025654,0.0065706577,0.02629586,0.019322807,0.0059510753,0.03560242,0.011190751,0.009083113,0,0,0,0.11616014,0.011153892,0.029643577,0,0.08584766,0.0011586623,0.012883163,0.006347931,0.064442284,0.007183812,0,0.043087702,0.009957424,0.082864076,0.1350576,0.0068540527,0.008172071,0.025571685,0.008150873,0,0.11180381,0.020926114,0.09742926,0.0041617104,0,0,0,0.008102957,0.0038306075,0,0,0.03256605,0.009680568,0,0.016712226,0.012830541,0,0.0051719435,0.06377607,0.007817631,0.056797545,0,0.010665289,0,0,0.052811693,0,0.012401772,0.032025926,0.032490894,0,0,0,0,0,0.012201444,0.008632126,0.022911811,0.05863921,0.024691308,0.002498327,0.008140267,0.04579179,0.021950051,0,0,0,0.07222717,0.010375051,0.00842103,0.008503394,0.02264724,0.0461262,0,0.023183653,0.008807735,0.009180749,0,0.01786461,0.031684037,0,0.13625897,0.011796212,0.009252606,0.02900712,0.027617097,0.011941999,0.05688848,0.0064270827,0,0,0.025238719,0.025580881,0,0,0.008056163,0.009475683,0,0,0.0156145515,0.006088445,0.00202068,0,0.009323228,0.016040804,0.003553314,0,0.0059634726,0,0,0,0.045647927,0,0,0.010851134,0.009932535,0.045164708,0.043779477,0.012608423,0.0064776666,0,0.033017375,0.009385407,0.06852477,0,0.008261453,0,0.010098294,0.051460914,0,0.011440811,0.037496176,0,0.0049273917,0.010334773,0,0,0.097833894,0,0.03921862,0,0,0.054913662,0,0,0,0.009419748,0.06433784,0,0,0.054984435,0.06194607,0.0123632215,0,0,0.06335412,0,0.008304516,0.027033018,0.0078258645,0,0,0.0475522,0.06286295,0.012044559,0.010908307,0.011703852,0,0,0.0070882873,0.046010014,0.00419476,0,0.00852538,0,0.009311208,0.03533244,0.008228598,0,0.043071717,0.005381072,0.055727333,0.037764963,0.02383667,0.07278038,0.007707846,0,0.011379765,0,0,0.06262232,0,0.03451522,0.013787109,0.042862926,0.007862054,0.005059206,0,0.002867538,0.05027772,0,0.01379573,0.014138726,0.05859499,0.1053086,0.0070896526,0.017079923,0,0.012903566,0,0.007874306,0,0.013490237,0.0020715583,0,0.008776103,0.008573873,0.0093027465,0,0.0072258655,0.024443522,0.045306366,0.040177394,0.035092223,0,0,0,0.0144832535,0.041788947,0.054669987,0,0.09524204,0.014890331,0.0731954,0,0.012052051,0,0.028858982,0.057112012,0,0,0.08155506,0,0.051429797,0,0.032874566,0,0.031054573,0.006334302,0.020469528,0,0,0.0118924705,0.0018559243,0.004289447,0.015062911,0,0.017559104,0,0,0.035003103,0.013528608,0,0.016985256,0.018587261,0.016257629,0.0074880295,0,0.036853112,0,0.020391373,0.00850858,0.057852283,0.009914317,0,0.012679639,0,0,0.02049418,0.0049447217,0,0.01091186,0,0,0.045311015,0.020089842,0,0,0.012860212,0,0,0.011716118,0.014297904,0.0047367997,0.011458634,0.057917025,0.014896416,0,0.08204053,0.014507304,0,0.0076231984,0,0.039514355,0.0072527267,0.022747654,0.05082019,0.050711565,0.013101391,0.02099056,0.010482825,0.014537079,0.07531072,0.007891948,0.06984402,0,0.008612479,0.007180137,0,0.03139999,0.0061596334,0.012928976,0.032089934,0.013479944,0.028597746,0.04971715,0.009240681,0.0071325,0,0.008929886,0.0050136293,0.011477552,0,0.0094644,0.10527592,0.003992802,0.01513294,0.008220206,0.015284153,0.010419514,0,0.0126597155,0,0.011046417,0.013021811,0,0.018425783,0,0,0,0.010725219,0,0,0.005725076,0.029128123,0.020167677,0.008912895,0,0.05740717,0.050996944,0.005307627,0.012928591,0,0,0,0.01636465,0.027248355,0,0,0.034358162,0,0,0,0.0075225253,0.07208521,0.006306782,0.042363394,0,0,0,0.0362746,0.102887034,0.0022140832,0.029485049,0.015464419,0.02657556,0.013042843,0.009066418,0.013518938,0.022875525,0,0.011569462,0.05462168,0.077257395,0.06741172,0.01255341,0.020680968,0,0,0.015482548,0,0.014197431,0.020352095,0.08663661,0.014317287,0.012731632,0.0086386725,0,0.004927721,0.021724833,0.04775494,0,0,0.047396276,0.05795204,0.0027833285,0.007719502,0.010661097,0.014554559,0.065450415,0.0065446016,0.011360852,0.121631324,0.00895186,0,0.07012229,0.016494006,0,0.0018460903,0,0.010730336,0.0068078865,0.01087823,0.041395865,0.082066566,0.01342022,0.0071833646,0.0065501533,0.012120771,0,0.026398309,0.007083677,0.0066868616,0.029904423,0.06059158,0.07429783,0,0,0.012384827,0.06939728,0.057636876,0.020549808,0,0,0.039874025,0.012578807,0.011126619,0,0,0,0.05263905,0.0791912,0.00799558,0.050822046,0,0.022211993,0.026047334,0.030766793,0.03456518,0.01449546,0.033177566,0,0,0,0.012319013,0.095025755,0.024243278,0,0.008853314,0,0.006450568,0,0.118323766,0.07947373,0.013837861,0.011377105,0.042782724,0.09119462,0.0128597,0.011606405,0.01979006,0.02409284,0.008917682,0.007592097,0.037191678,0,0,0]', '李俊.jpg', 'upload/20241023/de9b2482-fe13-4697-96c1-06aba8dac72b.jpg', 521, 501, 296, 399, '2024-10-23', '2024-10-23', '{{590.1159049770085,665.3062513372861},{736.8112287976801,663.8625748373834},{661.2410654412943,740.2610476701484},{611.6704869216974,812.4751519212405},{713.8414231123716,811.3582018521156}}');
