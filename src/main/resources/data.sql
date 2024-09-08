-- Insert into the farm table
INSERT INTO farm (id, name) VALUES (1, 'MyFarm');
INSERT INTO farm (id, name) VALUES (2, 'GreenFarm');

-- Insert into the season_report table for MyFarm (Multiple Seasons)
INSERT INTO season_report (id, farm_id, crop_type, planting_area, expected_product, actual_product, season)
VALUES (1, 1, 'Corn', 100, 150, 140, 'Spring 2024');
INSERT INTO season_report (id, farm_id, crop_type, planting_area, expected_product, actual_product, season)
VALUES (2, 1, 'Corn', 120, 160, 155, 'Summer 2024');
INSERT INTO season_report (id, farm_id, crop_type, planting_area, expected_product, actual_product, season)
VALUES (3, 1, 'Potato', 50, 80, 75, 'Spring 2024');

-- Insert into the season_report table for GreenFarm (Multiple Seasons)
INSERT INTO season_report (id, farm_id, crop_type, planting_area, expected_product, actual_product, season)
VALUES (4, 2, 'Wheat', 120, 200, 190, 'Spring 2024');
INSERT INTO season_report (id, farm_id, crop_type, planting_area, expected_product, actual_product, season)
VALUES (5, 2, 'Wheat', 140, 210, 205, 'Summer 2024');
INSERT INTO season_report (id, farm_id, crop_type, planting_area, expected_product, actual_product, season)
VALUES (6, 2, 'Barley', 90, 110, 100, 'Fall 2024');