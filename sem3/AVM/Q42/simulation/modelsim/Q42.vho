-- Copyright (C) 1991-2013 Altera Corporation
-- Your use of Altera Corporation's design tools, logic functions 
-- and other software and tools, and its AMPP partner logic 
-- functions, and any output files from any of the foregoing 
-- (including device programming or simulation files), and any 
-- associated documentation or information are expressly subject 
-- to the terms and conditions of the Altera Program License 
-- Subscription Agreement, Altera MegaCore Function License 
-- Agreement, or other applicable license agreement, including, 
-- without limitation, that your use is for the sole purpose of 
-- programming logic devices manufactured by Altera and sold by 
-- Altera or its authorized distributors.  Please refer to the 
-- applicable agreement for further details.

-- VENDOR "Altera"
-- PROGRAM "Quartus II 64-Bit"
-- VERSION "Version 13.1.0 Build 162 10/23/2013 SJ Web Edition"

-- DATE "11/05/2023 23:24:26"

-- 
-- Device: Altera EP4CGX15BF14C6 Package FBGA169
-- 

-- 
-- This VHDL file should be used for ModelSim-Altera (VHDL) only
-- 

LIBRARY ALTERA;
LIBRARY CYCLONEIV;
LIBRARY IEEE;
USE ALTERA.ALTERA_PRIMITIVES_COMPONENTS.ALL;
USE CYCLONEIV.CYCLONEIV_COMPONENTS.ALL;
USE IEEE.STD_LOGIC_1164.ALL;

ENTITY 	Q42 IS
    PORT (
	a : IN std_logic;
	b : IN std_logic;
	clock : IN std_logic;
	q : OUT std_logic_vector(3 DOWNTO 0)
	);
END Q42;

-- Design Ports Information
-- q[0]	=>  Location: PIN_N4,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- q[1]	=>  Location: PIN_M6,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- q[2]	=>  Location: PIN_N6,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- q[3]	=>  Location: PIN_M4,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- b	=>  Location: PIN_L7,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- clock	=>  Location: PIN_J7,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- a	=>  Location: PIN_L5,	 I/O Standard: 2.5 V,	 Current Strength: Default


ARCHITECTURE structure OF Q42 IS
SIGNAL gnd : std_logic := '0';
SIGNAL vcc : std_logic := '1';
SIGNAL unknown : std_logic := 'X';
SIGNAL devoe : std_logic := '1';
SIGNAL devclrn : std_logic := '1';
SIGNAL devpor : std_logic := '1';
SIGNAL ww_devoe : std_logic;
SIGNAL ww_devclrn : std_logic;
SIGNAL ww_devpor : std_logic;
SIGNAL ww_a : std_logic;
SIGNAL ww_b : std_logic;
SIGNAL ww_clock : std_logic;
SIGNAL ww_q : std_logic_vector(3 DOWNTO 0);
SIGNAL \clock~inputclkctrl_INCLK_bus\ : std_logic_vector(3 DOWNTO 0);
SIGNAL \q[0]~output_o\ : std_logic;
SIGNAL \q[1]~output_o\ : std_logic;
SIGNAL \q[2]~output_o\ : std_logic;
SIGNAL \q[3]~output_o\ : std_logic;
SIGNAL \clock~input_o\ : std_logic;
SIGNAL \clock~inputclkctrl_outclk\ : std_logic;
SIGNAL \b~input_o\ : std_logic;
SIGNAL \a~input_o\ : std_logic;
SIGNAL \newq[2]~10_combout\ : std_logic;
SIGNAL \newq[2]~2_combout\ : std_logic;
SIGNAL \newq[2]~9_combout\ : std_logic;
SIGNAL \r[4]~0_combout\ : std_logic;
SIGNAL \newq[1]~3_combout\ : std_logic;
SIGNAL \newq[0]~4_combout\ : std_logic;
SIGNAL \newq[2]~11_combout\ : std_logic;
SIGNAL \$00002~q\ : std_logic;
SIGNAL \newq[1]~7_combout\ : std_logic;
SIGNAL \newq[1]~6_combout\ : std_logic;
SIGNAL \newq[1]~8_combout\ : std_logic;
SIGNAL \$00001~q\ : std_logic;
SIGNAL \newq[3]~12_combout\ : std_logic;
SIGNAL \newq[3]~13_combout\ : std_logic;
SIGNAL \$00003~q\ : std_logic;
SIGNAL \newq[0]~1_combout\ : std_logic;
SIGNAL \newq[0]~0_combout\ : std_logic;
SIGNAL \newq[0]~5_combout\ : std_logic;
SIGNAL \$00000~q\ : std_logic;

BEGIN

ww_a <= a;
ww_b <= b;
ww_clock <= clock;
q <= ww_q;
ww_devoe <= devoe;
ww_devclrn <= devclrn;
ww_devpor <= devpor;

\clock~inputclkctrl_INCLK_bus\ <= (vcc & vcc & vcc & \clock~input_o\);

-- Location: IOOBUF_X10_Y0_N9
\q[0]~output\ : cycloneiv_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false")
-- pragma translate_on
PORT MAP (
	i => \$00000~q\,
	devoe => ww_devoe,
	o => \q[0]~output_o\);

-- Location: IOOBUF_X12_Y0_N9
\q[1]~output\ : cycloneiv_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false")
-- pragma translate_on
PORT MAP (
	i => \$00001~q\,
	devoe => ww_devoe,
	o => \q[1]~output_o\);

-- Location: IOOBUF_X12_Y0_N2
\q[2]~output\ : cycloneiv_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false")
-- pragma translate_on
PORT MAP (
	i => \$00002~q\,
	devoe => ww_devoe,
	o => \q[2]~output_o\);

-- Location: IOOBUF_X8_Y0_N2
\q[3]~output\ : cycloneiv_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false")
-- pragma translate_on
PORT MAP (
	i => \$00003~q\,
	devoe => ww_devoe,
	o => \q[3]~output_o\);

-- Location: IOIBUF_X16_Y0_N15
\clock~input\ : cycloneiv_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_clock,
	o => \clock~input_o\);

-- Location: CLKCTRL_G17
\clock~inputclkctrl\ : cycloneiv_clkctrl
-- pragma translate_off
GENERIC MAP (
	clock_type => "global clock",
	ena_register_mode => "none")
-- pragma translate_on
PORT MAP (
	inclk => \clock~inputclkctrl_INCLK_bus\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	outclk => \clock~inputclkctrl_outclk\);

-- Location: IOIBUF_X14_Y0_N1
\b~input\ : cycloneiv_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_b,
	o => \b~input_o\);

-- Location: IOIBUF_X14_Y0_N8
\a~input\ : cycloneiv_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_a,
	o => \a~input_o\);

-- Location: LCCOMB_X13_Y1_N22
\newq[2]~10\ : cycloneiv_lcell_comb
-- Equation(s):
-- \newq[2]~10_combout\ = (\$00003~q\ & (((\b~input_o\) # (\$00002~q\)))) # (!\$00003~q\ & (\a~input_o\ & ((\$00002~q\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1111101011000000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \a~input_o\,
	datab => \b~input_o\,
	datac => \$00003~q\,
	datad => \$00002~q\,
	combout => \newq[2]~10_combout\);

-- Location: LCCOMB_X13_Y1_N0
\newq[2]~2\ : cycloneiv_lcell_comb
-- Equation(s):
-- \newq[2]~2_combout\ = (\$00000~q\ & \$00001~q\)

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1111000000000000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	datac => \$00000~q\,
	datad => \$00001~q\,
	combout => \newq[2]~2_combout\);

-- Location: LCCOMB_X13_Y1_N8
\newq[2]~9\ : cycloneiv_lcell_comb
-- Equation(s):
-- \newq[2]~9_combout\ = (\$00000~q\ & (!\$00003~q\ & (!\$00001~q\ & !\$00002~q\))) # (!\$00000~q\ & (\$00003~q\ $ ((\$00001~q\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0000011000010110",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \$00003~q\,
	datab => \$00001~q\,
	datac => \$00000~q\,
	datad => \$00002~q\,
	combout => \newq[2]~9_combout\);

-- Location: LCCOMB_X13_Y1_N4
\r[4]~0\ : cycloneiv_lcell_comb
-- Equation(s):
-- \r[4]~0_combout\ = (\$00002~q\ & (!\$00001~q\ & (!\$00003~q\ & !\$00000~q\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0000000000000010",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \$00002~q\,
	datab => \$00001~q\,
	datac => \$00003~q\,
	datad => \$00000~q\,
	combout => \r[4]~0_combout\);

-- Location: LCCOMB_X13_Y1_N2
\newq[1]~3\ : cycloneiv_lcell_comb
-- Equation(s):
-- \newq[1]~3_combout\ = ((\$00000~q\ & ((\$00001~q\) # (!\$00002~q\))) # (!\$00000~q\ & ((\$00002~q\) # (!\$00001~q\)))) # (!\$00003~q\)

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1101111110111111",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \$00000~q\,
	datab => \$00001~q\,
	datac => \$00003~q\,
	datad => \$00002~q\,
	combout => \newq[1]~3_combout\);

-- Location: LCCOMB_X13_Y1_N18
\newq[0]~4\ : cycloneiv_lcell_comb
-- Equation(s):
-- \newq[0]~4_combout\ = (\newq[1]~3_combout\ & ((\b~input_o\) # (!\r[4]~0_combout\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1100111100000000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	datab => \b~input_o\,
	datac => \r[4]~0_combout\,
	datad => \newq[1]~3_combout\,
	combout => \newq[0]~4_combout\);

-- Location: LCCOMB_X13_Y1_N12
\newq[2]~11\ : cycloneiv_lcell_comb
-- Equation(s):
-- \newq[2]~11_combout\ = (\newq[2]~9_combout\) # (((\newq[2]~10_combout\ & \newq[2]~2_combout\)) # (!\newq[0]~4_combout\))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1111100011111111",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \newq[2]~10_combout\,
	datab => \newq[2]~2_combout\,
	datac => \newq[2]~9_combout\,
	datad => \newq[0]~4_combout\,
	combout => \newq[2]~11_combout\);

-- Location: FF_X13_Y1_N13
\$00002\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clock~inputclkctrl_outclk\,
	d => \newq[2]~11_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \$00002~q\);

-- Location: LCCOMB_X13_Y1_N10
\newq[1]~7\ : cycloneiv_lcell_comb
-- Equation(s):
-- \newq[1]~7_combout\ = (\$00001~q\ & ((\$00003~q\) # ((!\$00000~q\ & \$00002~q\)))) # (!\$00001~q\ & ((\$00003~q\ & ((\$00002~q\))) # (!\$00003~q\ & (\$00000~q\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1111110001001010",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \$00000~q\,
	datab => \$00002~q\,
	datac => \$00001~q\,
	datad => \$00003~q\,
	combout => \newq[1]~7_combout\);

-- Location: LCCOMB_X13_Y1_N16
\newq[1]~6\ : cycloneiv_lcell_comb
-- Equation(s):
-- \newq[1]~6_combout\ = (\$00000~q\ & ((\a~input_o\))) # (!\$00000~q\ & (\b~input_o\))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1111000011001100",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	datab => \b~input_o\,
	datac => \a~input_o\,
	datad => \$00000~q\,
	combout => \newq[1]~6_combout\);

-- Location: LCCOMB_X13_Y1_N14
\newq[1]~8\ : cycloneiv_lcell_comb
-- Equation(s):
-- \newq[1]~8_combout\ = ((!\newq[1]~7_combout\ & ((\newq[1]~6_combout\) # (!\$00002~q\)))) # (!\newq[1]~3_combout\)

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0011111100110111",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \$00002~q\,
	datab => \newq[1]~3_combout\,
	datac => \newq[1]~7_combout\,
	datad => \newq[1]~6_combout\,
	combout => \newq[1]~8_combout\);

-- Location: FF_X13_Y1_N15
\$00001\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clock~inputclkctrl_outclk\,
	d => \newq[1]~8_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \$00001~q\);

-- Location: LCCOMB_X13_Y1_N24
\newq[3]~12\ : cycloneiv_lcell_comb
-- Equation(s):
-- \newq[3]~12_combout\ = (\$00003~q\ & ((!\$00000~q\) # (!\b~input_o\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0011000011110000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	datab => \b~input_o\,
	datac => \$00003~q\,
	datad => \$00000~q\,
	combout => \newq[3]~12_combout\);

-- Location: LCCOMB_X13_Y1_N26
\newq[3]~13\ : cycloneiv_lcell_comb
-- Equation(s):
-- \newq[3]~13_combout\ = (\$00001~q\ & ((\newq[3]~12_combout\ & ((!\$00000~q\))) # (!\newq[3]~12_combout\ & (!\$00002~q\)))) # (!\$00001~q\ & (\$00002~q\ & ((\$00000~q\) # (!\newq[3]~12_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0100101001100110",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \$00001~q\,
	datab => \$00002~q\,
	datac => \$00000~q\,
	datad => \newq[3]~12_combout\,
	combout => \newq[3]~13_combout\);

-- Location: FF_X13_Y1_N27
\$00003\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clock~inputclkctrl_outclk\,
	d => \newq[3]~13_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \$00003~q\);

-- Location: LCCOMB_X13_Y1_N30
\newq[0]~1\ : cycloneiv_lcell_comb
-- Equation(s):
-- \newq[0]~1_combout\ = (\$00002~q\ & ((\$00003~q\))) # (!\$00002~q\ & (!\b~input_o\))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1111000000110011",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	datab => \b~input_o\,
	datac => \$00003~q\,
	datad => \$00002~q\,
	combout => \newq[0]~1_combout\);

-- Location: LCCOMB_X13_Y1_N20
\newq[0]~0\ : cycloneiv_lcell_comb
-- Equation(s):
-- \newq[0]~0_combout\ = (\$00000~q\ & (!\$00003~q\ & ((!\$00002~q\) # (!\$00001~q\)))) # (!\$00000~q\ & (!\$00001~q\ & ((\$00003~q\) # (!\$00002~q\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0001001001010011",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \$00003~q\,
	datab => \$00001~q\,
	datac => \$00000~q\,
	datad => \$00002~q\,
	combout => \newq[0]~0_combout\);

-- Location: LCCOMB_X13_Y1_N28
\newq[0]~5\ : cycloneiv_lcell_comb
-- Equation(s):
-- \newq[0]~5_combout\ = (\newq[0]~0_combout\) # (((\newq[0]~1_combout\ & \newq[2]~2_combout\)) # (!\newq[0]~4_combout\))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1111100011111111",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \newq[0]~1_combout\,
	datab => \newq[2]~2_combout\,
	datac => \newq[0]~0_combout\,
	datad => \newq[0]~4_combout\,
	combout => \newq[0]~5_combout\);

-- Location: FF_X13_Y1_N29
\$00000\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clock~inputclkctrl_outclk\,
	d => \newq[0]~5_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \$00000~q\);

ww_q(0) <= \q[0]~output_o\;

ww_q(1) <= \q[1]~output_o\;

ww_q(2) <= \q[2]~output_o\;

ww_q(3) <= \q[3]~output_o\;
END structure;


