/* To update the customer's phone number, we can use the following query*/
UPDATE tblSubscriptionInfo
SET customer_contact_phone = 'NEW_PHONE_NUMBER';
/* Replace 'NEW_PHONE_NUMBER' with the new contact phone number*/

/* To speed up the update query and improve performance we can do the following:
- Indexing by using an index such as 'customer_id' column to quickly locate the row to be updated
i.e WHERE customer_id = YOUR_CUSTOMER_ID;

- Update in batches to reduce the number of individual queries
- Use database connection pooling to manage database connections efficiently and reduce overhead of establishing new connection for each update */

/* 1. Number of subscribers whose subscriptions will be ending in 2023*/
SELECT COUNT(*) AS number_of_subscribers_ending_2023
FROM tblSubscriptionInfo
WHERE subscription_end_date >= '2023-01-01' AND subscription_end_date <= '2023-12-31';


/* 2. Number of subscribers who have subscribed for more than 3 months in 2022 */
SELECT COUNT(*) AS number_of_subscribers_more_than_3_months_2022
FROM tblSubscriptionInfo
WHERE subscription_start_date >= '2022-01-01' AND subscription_start_date <= '2022-12-31'
AND DATEDIFF(MONTH, subscription_start_date, subscription_end_date) > 3;


/* 3. Subscribers who have subscribed for more than two products */
SELECT customer_id, customer_name
FROM tblSubscriptionInfo
GROUP BY customer_id, customer_name
HAVING COUNT(DISTINCT product_id) > 2;


/* 4. Product with the most/2ndmost/3rdmost number of subscribers in 2022 */
SELECT product_name, COUNT(*) AS num_subscribers
FROM tblSubscriptionInfo
WHERE subscription_start_date >= '2022-01-01' AND subscription_end_date <= '2022-12-31'
GROUP BY product_name
ORDER BY num_subscribers DESC
LIMIT 3;


/* 5. Number of subscribers who have re-subscribed more than once for each product */
SELECT customer_id, customer_name
FROM tblSubscriptionInfo
GROUP BY customer_id, customer_name, product_id
HAVING COUNT(*) > 1;


/* 6. Subscribers who have re-subscribed a higher version of the product in 2023 - for example Autocad 2022 to Autocad 2023 */
SELECT customer_id, customer_name
FROM tblSubscriptionInfo
WHERE subscription_start_date >= '2023-01-01' AND subscription_start_date <= '2023-12-31'
AND RIGHT(product_name, 4) > '2022'; -- Assuming product version format is "Autocad 202X"


