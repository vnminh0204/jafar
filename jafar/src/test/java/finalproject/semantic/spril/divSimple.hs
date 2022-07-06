import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue (-1)) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 100) regA 
    , Push regA 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Equal regB reg0 regD 
    , Branch regD (Abs 163) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 17) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 13) 
    , Push regC 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 99) regA 
    , Push regA 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Equal regB reg0 regD 
    , Branch regD (Abs 163) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 37) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 33) 
    , Push regC 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 17) regA 
    , Push regA 
    , Load (ImmValue 17) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Equal regB reg0 regD 
    , Branch regD (Abs 163) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 57) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 53) 
    , Push regC 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 91) regA 
    , Push regA 
    , Load (ImmValue 14) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Equal regB reg0 regD 
    , Branch regD (Abs 163) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 77) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 73) 
    , Push regC 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 60) regA 
    , Push regA 
    , Load (ImmValue 12) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Equal regB reg0 regD 
    , Branch regD (Abs 163) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 97) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 93) 
    , Push regC 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 121) regA 
    , Push regA 
    , Load (ImmValue 11) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Equal regB reg0 regD 
    , Branch regD (Abs 163) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 117) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 113) 
    , Push regC 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Load (ImmValue 87) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Equal regB reg0 regD 
    , Branch regD (Abs 163) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 137) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 133) 
    , Push regC 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 15) regA 
    , Push regA 
    , Load (ImmValue 16) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Equal regB reg0 regD 
    , Branch regD (Abs 163) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 157) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 153) 
    , Push regC 
    , Pop regA 
    , WriteInstr regA numberIO 
    , EndProg 
    ]

main = run [prog0]

