import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 24) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 15) regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Load (ImmValue 7) regA 
    , Push regA 
    , Load (ImmValue 8) regA 
    , Push regA 
    , Load (ImmValue 9) regA 
    , Push regA 
    , Load (ImmValue 10) regA 
    , Push regA 
    , Load (ImmValue 11) regA 
    , Push regA 
    , Load (ImmValue 8) regA 
    , Push regA 
    , Load (ImmValue 9) regA 
    , Push regA 
    , Load (ImmValue 15) regA 
    , Push regA 
    , Load (ImmValue 17) regA 
    , Push regA 
    , Load (ImmValue 12) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
    , Pop regA 
    , Load (ImmValue 11) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 10) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 9) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 8) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 7) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 6) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 5) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 4) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 3) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regD 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 263) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 263) 
    , Pop regD 
    , Load (ImmValue 6) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 263) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 263) 
    , Pop regD 
    , Load (ImmValue 2) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 263) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 263) 
    , Pop regD 
    , Load (ImmValue 6) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 263) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 263) 
    , Pop regD 
    , Load (ImmValue 2) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regB 
    , Pop regA 
    , Load (ImmValue 1) regC 
    , Load (IndAddr regA) regD 
    , Load (IndAddr regB) regE 
    , Compute Equal regD regE regD 
    , Compute And regC regD regC 
    , Load (ImmValue 1) regD 
    , Compute Add regA regD regA 
    , Compute Add regB regD regB 
    , Load (IndAddr regA) regD 
    , Load (IndAddr regB) regE 
    , Compute Equal regD regE regD 
    , Compute And regC regD regC 
    , Load (ImmValue 1) regD 
    , Compute Add regA regD regA 
    , Compute Add regB regD regB 
    , Push regC 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 13) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Load (ImmValue 11) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 10) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 9) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 8) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 7) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 6) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 5) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 4) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 3) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 2) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 1) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 0) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 10000) regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , EndProg 
    ]

main = run [prog0]

