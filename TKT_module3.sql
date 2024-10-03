create database Module3; 
use Module3; 
create table MatBangThue (
maMb varchar(50) primary key,
dienTich double,
trThai int,
tang int,
loaiVp int,
giaThue double,
startDate date,
endDate date
);
INSERT INTO MatBangThue (maMb, dienTich, trThai, tang, loaiVp, giaThue, startDate, endDate)
VALUES
('TTT-TS-12', 1000  , 1,5,1,500000, '2024-11-11' , '2026-11-11'),
('TTT-TS-13', 400  , 1,5,1,500000, '2025-03-15' , '2025-11-11'),
('TTT-TS-14', 600  , 1,5,1,500000, '2024-09-11' , '2024-09-11'); 


