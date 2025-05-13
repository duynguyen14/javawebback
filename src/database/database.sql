use foxystore;

insert into catalog(catalog_id ,name) 
values
(1,"Thời trang nam"),
(2,"Thời trang nữ"),
(3, "Phụ kiện thời trang"),
(4,"Thời trang trẻ em");
select *from catalog;

insert into category (category_id, name, catalog_id)
values
(31,"Giày dép",3),
(32,"Túi xách",3),
(33,"Thắt lưng",3),
(34,"Mũ nón",3),
(35,"Kính mát",3),
(36,"Đồng hồ",3),
(37,"Trang sức",3);
select *from category;

INSERT INTO product (product_id, created_date, description, name, price, quantity, sold_count, category_id) 
VALUES
(180, '2024-06-12', 'Giày Nike SB Force 58 Skate Red White Like Auth', 'Giày Nike SB Force 58 Skate Red White Like Auth', 850000, 1000, 230, 31),
(181, '2024-09-25', 'Giày Nike Air Force 1 Low All White Best Quality', 'Giày Nike Air Force 1 Low All White Best Quality', 900000, 1000, 115, 31),
(182, '2025-01-03', 'Giày Nike Air Force 1 White Brown Like Auth', 'Giày Nike Air Force 1 White Brown Like Auth', 750000, 1000, 378, 31),
(183, '2024-10-14', 'Giày MLB Chunky Liner New York Yankees ‘Black White’ Best Quality', 'Giày MLB Chunky Liner New York Yankees ‘Black White’ Best Quality', 900000, 1000, 269, 31),
(184, '2024-08-01', 'Giày Nike Air Force 1 Low ID ‘Gucci’ Like Auth', 'Giày Nike Air Force 1 Low ID ‘Gucci’ Like Auth', 800000, 1000, 197, 31),
(185, '2024-12-15', 'Giày Nike Air Jordan 1 Low SE ‘Washed Denim’ Like Auth', 'Giày Nike Air Jordan 1 Low SE ‘Washed Denim’ Like Auth', 900000, 1000, 489, 31),
(186, '2024-11-09', 'Giày Nike Air Jordan 1 Low ‘Punk Rock’ Like Auth', 'Giày Nike Air Jordan 1 Low ‘Punk Rock’ Like Auth', 900000, 1000, 298, 31),
(187, '2025-04-20', 'Giày Nike Air Jordan 1 Low SE ‘Paw Print’ Like Auth', 'Giày Nike Air Jordan 1 Low SE ‘Paw Print’ Like Auth', 850000, 1000, 124, 31),
(188, '2024-05-25', 'Nike Air Jordan 1 Low Phantom Denim Like Auth', 'Nike Air Jordan 1 Low Phantom Denim Like Auth', 900000, 1000, 352, 31),
(189, '2025-03-14', 'Giày Nike Dunk Low ‘Year Of The Dragon’ 2024 Best Quality', 'Giày Nike Dunk Low ‘Year Of The Dragon’ 2024 Best Quality', 1200000, 1000, 401, 31),
(190, '2024-07-30', 'Giày Nike Dunk Low ‘Rose Whisper’ Like Auth', 'Giày Nike Dunk Low ‘Rose Whisper’ Like Auth', 850000, 1000, 167, 31),
(191, '2024-10-08', 'Giày Nike SB Dunk Low ‘Union Passport Pack Pistachio’ Like Auth', 'Giày Nike SB Dunk Low ‘Union Passport Pack Pistachio’ Like Auth', 1300000, 1000, 290, 31),
(192, '2024-09-01', 'Giày Nike Air Sb Jordan Otomo Siêu Cấp', 'Giày Nike Air Sb Jordan Otomo Siêu Cấp', 750000, 1000, 382, 31),
(193, '2024-07-18', 'Giày Nike Air Sb Jordan Otomo Siêu Cấp', 'Giày Nike Air Sb Jordan Otomo Siêu Cấp', 750000, 1000, 164, 31),
(194, '2025-02-05', 'Giày Adidas Superstar White Collegiate Green Like Auth', 'Giày Adidas Superstar White Collegiate Green Like Auth', 800000, 1000, 219, 31),
(195, '2025-01-27', 'Giày Adidas Superstar André Saraiva Chalk White Black', 'Giày Adidas Superstar André Saraiva Chalk White Black', 850000, 1000, 312, 31),
(196, '2024-11-20', 'Giày Adidas Superstar White Aluminium Like Auth', 'Giày Adidas Superstar White Aluminium Like Auth', 850000, 1000, 142, 31),
(197, '2024-12-08', 'Giày Adidas Superstar ‘Beige’ Flower Like Auth', 'Giày Adidas Superstar ‘Beige’ Flower Like Auth', 900000, 1000, 201, 31),
(198, '2025-03-03', 'Giày Adidas Superstar Kitty Siêu Cấp', 'Giày Adidas Superstar Kitty Siêu Cấp', 950000, 1000, 283, 31),
(199, '2024-06-22', 'Giày Adidas Superstar Cappuccino Pink Like Auth', 'Giày Adidas Superstar Cappuccino Pink Like Auth', 850000, 1000, 173, 31);



INSERT INTO product (product_id, created_date, name, price, quantity, sold_count, category_id)
VALUES
(200, '2024-12-28', 'Túi xách tay mini nhấn chân quai - TOT 0197 - Màu xanh da trời', 908000, 1000, 484, 32),
(201, '2024-06-24', 'Túi đeo vai hình thang nắp gập nhấn khóa kim loại - SHO 0279 - Màu kem', 1271000, 1000, 147, 32),
(202, '2024-07-21', 'Túi xách tay hình thang basic quai đôi - TOT 0194 - Màu kem', 1026000, 1000, 431, 32),
(203, '2024-09-15', 'Túi xách tay phom mềm dập nổi họa tiết - SAT 0335 - Màu kem', 1154000, 1000, 46, 32),
(204, '2024-06-11', 'Túi xách tay hình thang basic quai đôi - TOT 0194 - Màu đen', 1026000, 1000, 47, 32),
(205, '2024-06-18', 'Túi xách tay hình thang kiểu tối giản - TOT 0192 - Màu be đậm', 928000, 1000, 480, 32),
(206, '2024-08-22', 'Túi Xách Lớn Tote Bag Tay Cầm Dạng Ống', 806000, 1000, 495, 32),
(207, '2024-07-27', 'Túi Xách Nhỏ Phối Tay Cầm Dây Vải', 669000, 1000, 153, 32),
(208, '2024-10-08', 'Túi Xách Nhỏ Đeo Vai Camping', 674000, 1000, 255, 32),
(209, '2024-06-30', 'Túi Xách Nhỏ Tay Cầm Trang Trí Khoá', 602000, 1000, 390, 32),
(210, '2025-03-07', 'Túi Xách Nhỏ Khoá Khắc Hoạ Tiết Houndstooth', 639000, 1000, 153, 32),
(211, '2025-01-13', 'Túi Xách Trung Dạng Tote Form Mềm', 639000, 1000, 420, 32),
(212, '2025-02-15', 'Túi Xách Nhỏ Đeo Vai Hoạ Tiết Chần Bông', 599000, 1000, 58, 32),
(213, '2024-09-09', 'Túi Xách Nhỏ In Hoạ Tiết Chuyển Màu', 699000, 1000, 175, 32),
(214, '2025-03-16', 'Túi Xách Nhỏ Top Handle Có Dây Đeo Chéo', 674000, 1000, 121, 32),
(215, '2024-07-18', 'Túi Xách Nhỏ Tay Cầm Xoắn Phối Charm Trang Trí', 854000, 1000, 308, 32),
(216, '2025-03-25', 'Túi Xách Nhỏ Phối Tay Cầm Xoắn', 719000, 1000, 79, 32),
(217, '2024-11-25', 'Túi Xách Nữ Da TOGO Đeo Chéo & Xách Tay Đựng Laptop & Hồ Sơ SBM395', 1850000, 1000, 347, 32),
(218, '2024-11-06', 'Túi Da TOGO Đeo Chéo & Xách Tay Phong Cách Hiện Đại SBM363', 1790000, 1000, 105, 32),
(219, '2024-11-18', 'Túi Xách Nữ William POLO Đeo Chéo & Xách Tay Thời Trang SBM361', 1490000, 1000, 418, 32),
(220, '2025-02-12', 'Túi Xách Nữ Da TOGO Đeo Chéo & Xách Tay SBM364', 1690000, 1000, 273, 32),
(221, '2024-10-20', 'Túi Xách Nữ Daryna Convertible Xbody Flap', 2799000, 1000, 317, 32);


INSERT INTO product (product_id, created_date, name, price, quantity, sold_count, category_id)
VALUES
(222, '2024-07-12', 'Thắt lưng Monogram Neo', 550000, 1000, 243, 33),
(223, '2024-06-25', 'Thắt Lưng Quickfit Steven Leonardo', 560000, 1000, 132, 33),
(224, '2024-08-19', 'Thắt Lưng Da Quickfit Sid Leonardo', 399000, 1000, 408, 33),
(225, '2024-09-04', 'Thắt Lưng Devon', 499000, 1000, 291, 33),
(226, '2024-10-21', 'Thắt Lưng Nam Zuciani Khóa Kim Da Vân Trơn', 790000, 1000, 178, 33),
(227, '2024-11-16', 'Thắt Lưng Nam Zuciani Da Vân Cổ Điển', 850000, 1000, 346, 33),
(228, '2024-12-30', 'Thắt Lưng Nam Zuciani Đầu Khoá Vàng Kim Sang Trọng', 1220000, 1000, 400, 33),
(229, '2025-01-11', 'Thắt lưng dây bản nhấn chỉ nổi - WAI 0041 - Màu be đậm', 339000, 1000, 105, 33),
(230, '2025-01-20', 'Thắt lưng dây bản nhấn chỉ nổi - WAI 0041 - Màu đen', 339000, 1000, 56, 33),
(231, '2025-02-10', 'Thắt lưng dây mảnh nhấn khóa kiểu - WAI 0040 - Màu đen', 339000, 1000, 382, 33),
(232, '2025-02-15', 'Thắt lưng dây mảnh nhấn khóa gài - WAI 0039 - Màu kem', 339000, 1000, 172, 33),
(233, '2025-03-01', 'Thắt lưng khóa cài đầu tròn cổ điển - WAI 0038 - Màu nâu', 339000, 1000, 228, 33),
(234, '2025-03-09', 'Thắt Lưng Nam LV Cao Cấp Dây Lưng Nam Caro Mặt LV Cài Lỗ Mạ Vàng 24k Sang Trọng Đẳng Cấp', 339000, 1000, 395, 33),
(235, '2025-03-17', 'Thắt Lưng Nữ Bản Nhỏ Chữ D Mặt Khóa Mạ Bạc Dây Nịt Nam Nữ Chất Liệu Da May Viền Chỉn Chu Style Ulzzang T2', 120000, 1000, 167, 33),
(236, '2025-03-20', 'Thắt Lưng Nam Mặt Khóa LV Nguyên Khối Cao Cấp', 120000, 1000, 322, 33),
(237, '2025-03-28', 'Thắt Lưng Nam Cao Cấp Dây Lưng Nam Mặt Khóa Kim Nguyên Khối K025 Phong Cách Thời Trang Hàn Quốc Lịch Lãm TL7', 160000, 1000, 214, 33);


INSERT INTO product (product_id, created_date, name, price, quantity, sold_count, category_id)
VALUES
(238, '2024-06-30', 'Mũ Lưỡi Trai Xếp Ly P0070', 550000, 1000, 138, 34),
(239, '2024-07-05', 'Mũ Nồi P0069', 295000, 1000, 312, 34),
(240, '2024-07-12', 'Mũ Nồi P0068', 295000, 1000, 231, 34),
(241, '2024-07-18', 'Nón Rộng Vành Phối Lưới Thời Trang P0061', 495000, 1000, 119, 34),
(242, '2024-07-21', 'Nón Lưỡi Trai P0057', 295000, 1000, 268, 34),
(243, '2024-07-29', 'Nón Rộng Vành Thời Trang P0062', 495000, 1000, 342, 34),
(244, '2024-08-02', 'Nón Rộng Vành NO-000067', 250000, 1000, 190, 34),
(245, '2024-08-06', 'Nón Bucket NO-000018', 395000, 1000, 378, 34),
(246, '2024-08-11', 'Nón Lưỡi Trai NO-000010', 195000, 1000, 204, 34),
(247, '2024-08-15', 'Nón Rộng Vành NO-000007', 495000, 1000, 172, 34),
(248, '2024-08-19', 'Mũ Nồi NO-000002', 195000, 1000, 289, 34),
(249, '2024-08-24', 'Nón Rộng Vành NO-000001', 195000, 1000, 305, 34),
(250, '2024-08-29', 'Nón Rộng Vành NO-000004', 195000, 1000, 266, 34),
(251, '2024-09-03', 'Mũ Nồi P0066', 250000, 1000, 146, 34),
(252, '2024-09-09', 'Nón Rộng Vành NO-000006', 595000, 1000, 377, 34),
(253, '2024-09-14', 'Nón Rộng Vành NO-000005', 350000, 1000, 199, 34),
(254, '2024-09-19', 'Nón Lưỡi Trai NO-000008', 450000, 1000, 385, 34),
(255, '2024-09-24', 'Nón Lưỡi Trai NO-000009', 310000, 1000, 244, 34),
(256, '2024-09-30', 'Mũ Beret NO-000011', 495000, 1000, 158, 34),
(257, '2024-10-04', 'Mũ Rơm Rộng Vành NO-000012', 280000, 1000, 118, 34),
(258, '2024-10-10', 'Mũ Rơm Rộng Vành NO-000015', 250000, 1000, 301, 34);

INSERT INTO product (product_id, created_date, name, price, quantity, sold_count, category_id)
VALUES
(259, '2024-06-01', 'Kính mát gọng chữ nhật Rectangular Recycled Acetate', 1895000, 1000, 212, 35),
(260, '2024-06-04', 'Kính mát dáng mắt mèo Vesta Angula', 1790000, 1000, 178, 35),
(261, '2024-06-07', 'Kính mát gọng vuông Recycled Acetate & Leather Quilted', 1990000, 1000, 251, 35),
(262, '2024-06-10', 'Kính mát gọng cánh bướm Seraphina Panelled', 2150000, 1000, 303, 35),
(263, '2024-06-13', 'Kính mát gọng cánh bướm Jill Recycled Acetate Geometric Butterfly', 2090000, 1000, 269, 35),
(264, '2024-06-16', 'Kính mát dáng oval Chloe - SPE 0021 - Màu be', 712000, 1000, 194, 35),
(265, '2024-06-18', 'Kính mát dáng chữ nhật Zeyda - SPE 0019 - Màu be đậm', 682000, 1000, 236, 35),
(266, '2024-06-21', 'Kính mát dáng chữ nhật retro Joy - SPE 0017 - Màu xanh da trời', 682000, 1000, 158, 35),
(267, '2024-06-24', 'Kính mát dáng vuông Rachel - WAY 0064 - Màu đen', 712000, 1000, 281, 35),
(268, '2024-06-27', 'Kính mát dáng mắt mèo Veronica - CAT 0019 - Màu đen', 712000, 1000, 209, 35),
(269, '2024-06-30', 'Gọng kính dáng oval Clara - ROU 0052 - Màu đen', 712000, 1000, 187, 35),
(270, '2024-07-03', 'Gọng kính dáng mắt mèo Camilla - CAT 0015 - Màu be', 712000, 1000, 166, 35),
(271, '2024-07-06', 'Kính mắt trong gọng nhựa wayfarer - WAY 0062 - Màu vàng đậm', 468000, 1000, 122, 35),
(272, '2024-07-09', 'Kính mắt trong gọng nhựa wayfarer - WAY 0060 - Màu trong suốt', 468000, 1000, 198, 35),
(273, '2024-07-12', 'Kính mát gọng nhựa wayfarer - WAY 0052 - Màu nâu sáng', 584000, 1000, 244, 35),
(274, '2024-07-15', 'Kính mắt hình đa giác gọng nhỏ - SPE 0008 - Màu bạc', 535000, 1000, 173, 35);



select *from product;

-- Giày Nike SB Force 58 Skate Red White Like Auth (product_id = 180)
INSERT INTO image (image_id, description, image, product_id) VALUES
(1001, NULL, '1001', 180),
(1002, NULL, '1002', 180),
(1003, NULL, '1003', 180),
(1004, NULL, '1004', 180),
(1005, NULL, '1005', 180),

-- Giày Nike Air Force 1 Low All White Best Quality (product_id = 181)
(1006, NULL, '1006', 181),
(1007, NULL, '1007', 181),
(1008, NULL, '1008', 181),
(1009, NULL, '1009', 181),
(1010, NULL, '1010', 181),

-- Giày Nike Air Force 1 White Brown Like Auth (product_id = 182)
(1011, NULL, '1011', 182),
(1012, NULL, '1012', 182),
(1013, NULL, '1013', 182),
(1014, NULL, '1014', 182),
(1015, NULL, '1015', 182),

-- Giày MLB Chunky Liner New York Yankees ‘Black White’ Best Quality (product_id = 183)
(1016, NULL, '1016', 183),
(1017, NULL, '1017', 183),
(1018, NULL, '1018', 183),
(1019, NULL, '1019', 183),
(1020, NULL, '1020', 183),

-- Giày Nike Air Force 1 Low ID ‘Gucci’ Like Auth (product_id = 184)
(1021, NULL, '1021', 184),
(1022, NULL, '1022', 184),
(1023, NULL, '1023', 184),
(1024, NULL, '1024', 184),
(1025, NULL, '1025', 184),

-- Giày Nike Air Jordan 1 Low SE ‘Washed Denim’ Like Auth (product_id = 185)
(1026, NULL, '1026', 185),
(1027, NULL, '1027', 185),
(1028, NULL, '1028', 185),
(1029, NULL, '1029', 185),
(1030, NULL, '1030', 185),

-- Giày Nike Air Jordan 1 Low ‘Punk Rock’ Like Auth (product_id = 186)
(1031, NULL, '1031', 186),
(1032, NULL, '1032', 186),
(1033, NULL, '1033', 186),
(1034, NULL, '1034', 186),
(1035, NULL, '1035', 186),

-- Giày Nike Air Jordan 1 Low SE ‘Paw Print’ Like Auth (product_id = 187)
(1036, NULL, '1036', 187),
(1037, NULL, '1037', 187),
(1038, NULL, '1038', 187),
(1039, NULL, '1039', 187),
(1040, NULL, '1040', 187),

-- Nike Air Jordan 1 Low Phantom Denim Like Auth (product_id = 188)
(1041, NULL, '1041', 188),
(1042, NULL, '1042', 188),
(1043, NULL, '1043', 188),
(1044, NULL, '1044', 188),
(1045, NULL, '1045', 188),

-- Giày Nike Dunk Low ‘Year Of The Dragon’ 2024 Best Quality (product_id = 189)
(1046, NULL, '1046', 189),
(1047, NULL, '1047', 189),
(1048, NULL, '1048', 189),
(1049, NULL, '1049', 189),
(1050, NULL, '1050', 189),

-- Giày Nike Dunk Low ‘Rose Whisper’ Like Auth (product_id = 190)
(1051, NULL, '1051', 190),
(1052, NULL, '1052', 190),
(1053, NULL, '1053', 190),
(1054, NULL, '1054', 190),
(1055, NULL, '1055', 190),

-- Giày Nike SB Dunk Low ‘Union Passport Pack Pistachio’ Like Auth (product_id = 191)
(1056, NULL, '1056', 191),
(1057, NULL, '1057', 191),
(1058, NULL, '1058', 191),
(1059, NULL, '1059', 191),
(1060, NULL, '1060', 191),

-- Giày Nike Air Sb Jordan Otomo Siêu Cấp (product_id = 192)
(1061, NULL, '1061', 192),
(1062, NULL, '1062', 192),
(1063, NULL, '1063', 192),
(1064, NULL, '1064', 192),
(1065, NULL, '1065', 192),

-- Giày Nike Air Sb Jordan Otomo Siêu Cấp (product_id = 193) - bản lặp
(1066, NULL, '1066', 193),
(1067, NULL, '1067', 193),
(1068, NULL, '1068', 193),
(1069, NULL, '1069', 193),
(1070, NULL, '1070', 193),

-- Giày Adidas Superstar White Collegiate Green Like Auth (product_id = 194)
(1071, NULL, '1071', 194),
(1072, NULL, '1072', 194),
(1073, NULL, '1073', 194),
(1074, NULL, '1074', 194),
(1075, NULL, '1075', 194),

-- Giày Adidas Superstar André Saraiva Chalk White Black (product_id = 195)
(1076, NULL, '1076', 195),
(1077, NULL, '1077', 195),
(1078, NULL, '1078', 195),
(1079, NULL, '1079', 195),
(1080, NULL, '1080', 195),

-- Giày Adidas Superstar White Aluminium Like Auth (product_id = 196)
(1081, NULL, '1081', 196),
(1082, NULL, '1082', 196),
(1083, NULL, '1083', 196),
(1084, NULL, '1084', 196),
(1085, NULL, '1085', 196),

-- Giày Adidas Superstar ‘Beige’ Flower Like Auth (product_id = 197)
(1086, NULL, '1086', 197),
(1087, NULL, '1087', 197),
(1088, NULL, '1088', 197),
(1089, NULL, '1089', 197),
(1090, NULL, '1090', 197),

-- Giày Adidas Superstar Kitty Siêu Cấp (product_id = 198)
(1091, NULL, '1091', 198),
(1092, NULL, '1092', 198),
(1093, NULL, '1093', 198),
(1094, NULL, '1094', 198),
(1095, NULL, '1095', 198),

-- Giày Adidas Superstar Cappuccino Pink Like Auth (product_id = 199)
(1096, NULL, '1096', 199),
(1097, NULL, '1097', 199),
(1098, NULL, '1098', 199),
(1099, NULL, '1099', 199),
(1100, NULL, '1100', 199);



INSERT INTO image (image_id, description, image, product_id) VALUES
-- Túi xách tay mini nhấn chân quai - TOT 0197 - Màu xanh da trời (product_id = 200)
(1101, NULL, '1101', 200),
(1102, NULL, '1102', 200),
(1103, NULL, '1103', 200),
(1104, NULL, '1104', 200),
(1105, NULL, '1105', 200),

-- Túi đeo vai hình thang nắp gập nhấn khóa kim loại - SHO 0279 - Màu kem (product_id = 201)
(1106, NULL, '1106', 201),
(1107, NULL, '1107', 201),
(1108, NULL, '1108', 201),
(1109, NULL, '1109', 201),
(1110, NULL, '1110', 201),

-- Túi xách tay hình thang basic quai đôi - TOT 0194 - Màu kem (product_id = 202)
(1111, NULL, '1111', 202),
(1112, NULL, '1112', 202),
(1113, NULL, '1113', 202),
(1114, NULL, '1114', 202),
(1115, NULL, '1115', 202),

-- Túi xách tay phom mềm dập nổi họa tiết - SAT 0335 - Màu kem (product_id = 203)
(1116, NULL, '1116', 203),
(1117, NULL, '1117', 203),
(1118, NULL, '1118', 203),
(1119, NULL, '1119', 203),
(1120, NULL, '1120', 203),

-- Túi xách tay hình thang basic quai đôi - TOT 0194 - Màu đen (product_id = 204)
(1121, NULL, '1121', 204),
(1122, NULL, '1122', 204),
(1123, NULL, '1123', 204),
(1124, NULL, '1124', 204),
(1125, NULL, '1125', 204),

-- Túi xách tay hình thang kiểu tối giản - TOT 0192 - Màu be đậm (product_id = 205)
(1126, NULL, '1126', 205),
(1127, NULL, '1127', 205),
(1128, NULL, '1128', 205),
(1129, NULL, '1129', 205),
(1130, NULL, '1130', 205),

-- Túi Xách Lớn Tote Bag Tay Cầm Dạng Ống (product_id = 206)
(1131, NULL, '1131', 206),
(1132, NULL, '1132', 206),
(1133, NULL, '1133', 206),
(1134, NULL, '1134', 206),
(1135, NULL, '1135', 206),

-- Túi Xách Nhỏ Phối Tay Cầm Dây Vải (product_id = 207)
(1136, NULL, '1136', 207),
(1137, NULL, '1137', 207),
(1138, NULL, '1138', 207),
(1139, NULL, '1139', 207),
(1140, NULL, '1140', 207),

-- Túi Xách Nhỏ Đeo Vai Camping (product_id = 208)
(1141, NULL, '1141', 208),
(1142, NULL, '1142', 208),
(1143, NULL, '1143', 208),
(1144, NULL, '1144', 208),
(1145, NULL, '1145', 208),

-- Túi Xách Nhỏ Tay Cầm Trang Trí Khoá (product_id = 209)
(1146, NULL, '1146', 209),
(1147, NULL, '1147', 209),
(1148, NULL, '1148', 209),
(1149, NULL, '1149', 209),
(1150, NULL, '1150', 209),

-- Túi Xách Nhỏ Khoá Khắc Hoạ Tiết Houndstooth (product_id = 210)
(1151, NULL, '1151', 210),
(1152, NULL, '1152', 210),
(1153, NULL, '1153', 210),
(1154, NULL, '1154', 210),
(1155, NULL, '1155', 210),

-- Túi Xách Trung Dạng Tote Form Mềm (product_id = 211)
(1156, NULL, '1156', 211),
(1157, NULL, '1157', 211),
(1158, NULL, '1158', 211),
(1159, NULL, '1159', 211),
(1160, NULL, '1160', 211),

-- Túi Xách Nhỏ Đeo Vai Hoạ Tiết Chần Bông (product_id = 212)
(1161, NULL, '1161', 212),
(1162, NULL, '1162', 212),
(1163, NULL, '1163', 212),
(1164, NULL, '1164', 212),
(1165, NULL, '1165', 212),

-- Túi Xách Nhỏ In Hoạ Tiết Chuyển Màu (product_id = 213)
(1166, NULL, '1166', 213),
(1167, NULL, '1167', 213),
(1168, NULL, '1168', 213),
(1169, NULL, '1169', 213),
(1170, NULL, '1170', 213),

-- Túi Xách Nhỏ Top Handle Có Dây Đeo Chéo (product_id = 214)
(1171, NULL, '1171', 214),
(1172, NULL, '1172', 214),
(1173, NULL, '1173', 214),
(1174, NULL, '1174', 214),
(1175, NULL, '1175', 214),

-- Túi Xách Nhỏ Tay Cầm Xoắn Phối Charm Trang Trí (product_id = 215)
(1176, NULL, '1176', 215),
(1177, NULL, '1177', 215),
(1178, NULL, '1178', 215),
(1179, NULL, '1179', 215),
(1180, NULL, '1180', 215),

-- Túi Xách Nhỏ Phối Tay Cầm Xoắn (product_id = 216)
(1181, NULL, '1181', 216),
(1182, NULL, '1182', 216),
(1183, NULL, '1183', 216),
(1184, NULL, '1184', 216),
(1185, NULL, '1185', 216),

-- Túi Xách Nữ Da TOGO Đeo Chéo & Xách Tay Đựng Laptop & Hồ Sơ SBM395 (product_id = 217)
(1186, NULL, '1186', 217),
(1187, NULL, '1187', 217),
(1188, NULL, '1188', 217),
(1189, NULL, '1189', 217),
(1190, NULL, '1190', 217),

-- Túi Da TOGO Đeo Chéo & Xách Tay Phong Cách Hiện Đại SBM363 (product_id = 218)
(1191, NULL, '1191', 218),
(1192, NULL, '1192', 218),
(1193, NULL, '1193', 218),
(1194, NULL, '1194', 218),
(1195, NULL, '1195', 218),

-- Túi Xách Nữ William POLO Đeo Chéo & Xách Tay Thời Trang SBM361 (product_id = 219)
(1196, NULL, '1196', 219),
(1197, NULL, '1197', 219),
(1198, NULL, '1198', 219),
(1199, NULL, '1199', 219),
(1200, NULL, '1200', 219),

-- Túi Xách Nữ Da TOGO Đeo Chéo & Xách Tay SBM364 (product_id = 220)
(1201, NULL, '1201', 220),
(1202, NULL, '1202', 220),
(1203, NULL, '1203', 220),
(1204, NULL, '1204', 220),
(1205, NULL, '1205', 220),

-- Túi Xách Nữ Daryna Convertible Xbody Flap (product_id = 221)
(1206, NULL, '1206', 221),
(1207, NULL, '1207', 221),
(1208, NULL, '1208', 221),
(1209, NULL, '1209', 221),
(1210, NULL, '1210', 221);

-- Insert into image table
INSERT INTO image (image_id, description, image, product_id) VALUES
(1211, NULL, '1211', 222),
(1216, NULL, '1216', 223),
(1221, NULL, '1221', 224),
(1223, NULL, '1223', 224),
(1225, NULL, '1225', 224),
(1226, NULL, '1226', 225),
(1231, NULL, '1231', 226),
(1232, NULL, '1232', 226),
(1233, NULL, '1233', 226),
(1234, NULL, '1234', 226),
(1235, NULL, '1235', 226),
(1236, NULL, '1236', 227),
(1237, NULL, '1237', 227),
(1238, NULL, '1238', 227),
(1239, NULL, '1239', 227),
(1240, NULL, '1240', 227),
(1241, NULL, '1241', 228),
(1242, NULL, '1242', 228),
(1243, NULL, '1243', 228),
(1244, NULL, '1244', 228),
(1245, NULL, '1245', 228),
(1246, NULL, '1246', 229),
(1247, NULL, '1247', 229),
(1248, NULL, '1248', 229),
(1249, NULL, '1249', 229),
(1250, NULL, '1250', 229),
(1251, NULL, '1251', 230),
(1252, NULL, '1252', 230),
(1253, NULL, '1253', 230),
(1254, NULL, '1254', 230),
(1255, NULL, '1255', 230),
(1256, NULL, '1256', 231),
(1257, NULL, '1257', 231),
(1258, NULL, '1258', 231),
(1259, NULL, '1259', 231),
(1260, NULL, '1260', 231),
(1261, NULL, '1261', 232),
(1262, NULL, '1262', 232),
(1263, NULL, '1263', 232),
(1264, NULL, '1264', 232),
(1265, NULL, '1265', 232),
(1266, NULL, '1266', 233),
(1267, NULL, '1267', 233),
(1268, NULL, '1268', 233),
(1269, NULL, '1269', 233),
(1271, NULL, '1271', 234),
(1272, NULL, '1272', 234),
(1273, NULL, '1273', 234),
(1274, NULL, '1274', 234),
(1275, NULL, '1275', 234),
(1276, NULL, '1276', 235),
(1277, NULL, '1277', 235),
(1278, NULL, '1278', 235),
(1279, NULL, '1279', 235),
(1280, NULL, '1280', 235),
(1281, NULL, '1281', 236),
(1282, NULL, '1282', 236),
(1283, NULL, '1283', 236),
(1284, NULL, '1284', 236),
(1285, NULL, '1285', 236),
(1286, NULL, '1286', 237),
(1287, NULL, '1287', 237),
(1288, NULL, '1288', 237),
(1289, NULL, '1289', 237),
(1290, NULL, '1290', 237);

-- Insert into image table for the new products
INSERT INTO image (image_id, description, image, product_id) VALUES
(1291, NULL, '1291', 238),
(1292, NULL, '1292', 238),
(1293, NULL, '1293', 238),
(1294, NULL, '1294', 238),
(1296, NULL, '1296', 239),
(1297, NULL, '1297', 239),
(1298, NULL, '1298', 239),
(1301, NULL, '1301', 240),
(1302, NULL, '1302', 240),
(1303, NULL, '1303', 240),
(1306, NULL, '1306', 241),
(1307, NULL, '1307', 241),
(1308, NULL, '1308', 241),
(1309, NULL, '1309', 241),
(1310, NULL, '1310', 241),
(1311, NULL, '1311', 242),
(1312, NULL, '1312', 242),
(1313, NULL, '1313', 242),
(1314, NULL, '1314', 242),
(1316, NULL, '1316', 243),
(1317, NULL, '1317', 243),
(1318, NULL, '1318', 243),
(1319, NULL, '1319', 243),
(1320, NULL, '1320', 243),
(1321, NULL, '1321', 244),
(1322, NULL, '1322', 244),
(1323, NULL, '1323', 244),
(1324, NULL, '1324', 244),
(1325, NULL, '1325', 244),
(1326, NULL, '1326', 245),
(1327, NULL, '1327', 245),
(1328, NULL, '1328', 245),
(1331, NULL, '1331', 246),
(1332, NULL, '1332', 246),
(1333, NULL, '1333', 246),
(1334, NULL, '1334', 246),
(1335, NULL, '1335', 246),
(1336, NULL, '1336', 247),
(1337, NULL, '1337', 247),
(1338, NULL, '1338', 247),
(1339, NULL, '1339', 247),
(1340, NULL, '1340', 247),
(1341, NULL, '1341', 248),
(1342, NULL, '1342', 248),
(1343, NULL, '1343', 248),
(1344, NULL, '1344', 248),
(1346, NULL, '1346', 249),
(1347, NULL, '1347', 249),
(1348, NULL, '1348', 249),
(1349, NULL, '1349', 249),
(1350, NULL, '1350', 249),
(1351, NULL, '1351', 250),
(1352, NULL, '1352', 250),
(1353, NULL, '1353', 250),
(1354, NULL, '1354', 250),
(1355, NULL, '1355', 250),
(1356, NULL, '1356', 251),
(1357, NULL, '1357', 251),
(1358, NULL, '1358', 251),
(1359, NULL, '1359', 251),
(1360, NULL, '1360', 251),
(1361, NULL, '1361', 252),
(1362, NULL, '1362', 252),
(1363, NULL, '1363', 252),
(1364, NULL, '1364', 252),
(1365, NULL, '1365', 252),
(1366, NULL, '1366', 253),
(1367, NULL, '1367', 253),
(1368, NULL, '1368', 253),
(1369, NULL, '1369', 253),
(1370, NULL, '1370', 253),
(1371, NULL, '1371', 254),
(1372, NULL, '1372', 254),
(1373, NULL, '1373', 254),
(1374, NULL, '1374', 254),
(1375, NULL, '1375', 254),
(1376, NULL, '1376', 255),
(1377, NULL, '1377', 255),
(1378, NULL, '1378', 255),
(1379, NULL, '1379', 255),
(1380, NULL, '1380', 255),
(1381, NULL, '1381', 256),
(1382, NULL, '1382', 256),
(1383, NULL, '1383', 256),
(1384, NULL, '1384', 256),
(1385, NULL, '1385', 256),
(1386, NULL, '1386', 257),
(1387, NULL, '1387', 257),
(1388, NULL, '1388', 257),
(1389, NULL, '1389', 257),
(1390, NULL, '1390', 257),
(1391, NULL, '1391', 258),
(1392, NULL, '1392', 258),
(1393, NULL, '1393', 258),
(1394, NULL, '1394', 258),
(1395, NULL, '1395', 258);

INSERT INTO image (image_id, description, image, product_id) VALUES
(1396, NULL, '1396', 259),
(1397, NULL, '1397', 259),
(1398, NULL, '1398', 259),
(1399, NULL, '1399', 259),
(1400, NULL, '1400', 259),
(1401, NULL, '1401', 260),
(1402, NULL, '1402', 260),
(1403, NULL, '1403', 260),
(1404, NULL, '1404', 260),
(1405, NULL, '1405', 260),
(1406, NULL, '1406', 261),
(1407, NULL, '1407', 261),
(1408, NULL, '1408', 261),
(1409, NULL, '1409', 261),
(1410, NULL, '1410', 261),
(1411, NULL, '1411', 262),
(1412, NULL, '1412', 262),
(1413, NULL, '1413', 262),
(1414, NULL, '1414', 262),
(1415, NULL, '1415', 262),
(1416, NULL, '1416', 263),
(1417, NULL, '1417', 263),
(1418, NULL, '1418', 263),
(1419, NULL, '1419', 263),
(1420, NULL, '1420', 263),
(1421, NULL, '1421', 264),
(1422, NULL, '1422', 264),
(1423, NULL, '1423', 264),
(1424, NULL, '1424', 264),
(1425, NULL, '1425', 264),
(1426, NULL, '1426', 265),
(1427, NULL, '1427', 265),
(1428, NULL, '1428', 265),
(1429, NULL, '1429', 265),
(1430, NULL, '1430', 265),
(1431, NULL, '1431', 266),
(1432, NULL, '1432', 266),
(1433, NULL, '1433', 266),
(1434, NULL, '1434', 266),
(1435, NULL, '1435', 266),
(1436, NULL, '1436', 267),
(1437, NULL, '1437', 267),
(1438, NULL, '1438', 267),
(1439, NULL, '1439', 267),
(1440, NULL, '1440', 267),
(1441, NULL, '1441', 268),
(1442, NULL, '1442', 268),
(1443, NULL, '1443', 268),
(1444, NULL, '1444', 268),
(1445, NULL, '1445', 268),
(1446, NULL, '1446', 269),
(1447, NULL, '1447', 269),
(1448, NULL, '1448', 269),
(1449, NULL, '1449', 269),
(1450, NULL, '1450', 269),
(1451, NULL, '1451', 270),
(1452, NULL, '1452', 270),
(1453, NULL, '1453', 270),
(1454, NULL, '1454', 270),
(1455, NULL, '1455', 270),
(1456, NULL, '1456', 271),
(1457, NULL, '1457', 271),
(1458, NULL, '1458', 271),
(1459, NULL, '1459', 271),
(1460, NULL, '1460', 271),
(1461, NULL, '1461', 272),
(1462, NULL, '1462', 272),
(1463, NULL, '1463', 272),
(1464, NULL, '1464', 272),
(1465, NULL, '1465', 272),
(1466, NULL, '1466', 273),
(1467, NULL, '1467', 273),
(1468, NULL, '1468', 273),
(1469, NULL, '1469', 273),
(1470, NULL, '1470', 273),
(1471, NULL, '1471', 274),
(1472, NULL, '1472', 274),
(1473, NULL, '1473', 274),
(1474, NULL, '1474', 274),
(1475, NULL, '1475', 274);
select *from image;

insert into size(size_id, size_name) 
values
(1,"S"),
(2,"M"),


(3,"L"),
(4,"XL");

select *from size;
INSERT INTO product_size (product_id, size_id)
SELECT p.product_id, s.size_id
FROM product p
CROSS JOIN size s;
select *from product_size;
