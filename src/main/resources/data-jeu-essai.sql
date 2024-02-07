INSERT INTO `personne` (`admin`, `id`, `email`, `mot_de_passe`, `nom`, `prenom`) VALUES
(b'1', 1, 'a@a', '$2a$10$eDSWFcPCXkgBMklVE1NZYey35PZujTbWuJt3JzOAZB.Q4wEyQYFZu', 'Bernard', 'Pierre'),
(b'0', 2, 'b@a', '$2a$10$1GkM/IAX5QZ5gB15PYowiuRcDRwMoLiOMuJqBy.Ss3ZKAQLA.YOKK', 'l\'éponge', 'Bob');

INSERT INTO site (nom, coordgeo, adresse, responsable_id) VALUES
('Premier_site', 48856, 'Metz', 1),
('Deuxieme_site', 757977, 'Nancy', 1);

INSERT INTO local (nom, m2) VALUES
('Premier_loc', 300),
('Deuxieme_loc', 600);

INSERT INTO stock (quantite) VALUES
('40000'),
('20000');

INSERT INTO consommable (nom, cout) VALUES
('Poulet', 3),
('Courgette', 1.50);

INSERT INTO type (nom) VALUES ('Viandes'), ('Légumes');
