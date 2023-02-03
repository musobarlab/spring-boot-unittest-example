CREATE TABLE users(
    id uuid PRIMARY KEY NOT NULL DEFAULT uuid_generate_v1(), 
    full_name character varying NOT NULL, 
    email character varying UNIQUE NOT NULL,
    created_at timestamp with time zone NOT NULL DEFAULT timezone('utc'::text, now()), 
    updated_at timestamp with time zone NOT NULL DEFAULT timezone('utc'::text, now())
);